

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_REGISTRO)
        db.execSQL(CREATE_TABLE_MOVIMIENTOS)
        db.execSQL(CREATE_TABLE_ARCHIVOS_GUARDADOS)
        db.execSQL(CREATE_TABLE_REPORTES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_REGISTRO")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_INICIO_SESION")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_MOVIMIENTOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ARCHIVOS_GUARDADOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_REPORTES")
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "finanzapp.db"
        const val DATABASE_VERSION = 1

        const val TABLE_REGISTRO = "Registro"
        const val TABLE_INICIO_SESION = "InicioSesion"
        const val TABLE_MOVIMIENTOS = "Movimientos"
        const val TABLE_ARCHIVOS_GUARDADOS = "ArchivosGuardados"
        const val TABLE_REPORTES = "Reportes"

        // Instrucciones para crear tablas
        const val CREATE_TABLE_REGISTRO = """
            CREATE TABLE $TABLE_REGISTRO (
                ClienteID INTEGER PRIMARY KEY,
                Nombre VARCHAR(50) NOT NULL,
                Apellido VARCHAR(30) NOT NULL,
                Num_Celular VARCHAR(20) NOT NULL,
                Correo VARCHAR(50) NOT NULL,
                Direccion VARCHAR(50) NOT NULL,
                Nombre_Usuario VARCHAR(10) NOT NULL,
                Contraseña VARCHAR(8) NOT NULL
            );
        """
        const val CREATE_TABLE_MOVIMIENTOS = """
            CREATE TABLE $TABLE_MOVIMIENTOS (
                Id INTEGER PRIMARY KEY,
                Cantidad INTEGER,
                Tipo_Movimiento VARCHAR(50),
                ClienteID INTEGER,
                Categoria VARCHAR(50),
                Fecha_Movimiento DATE,
                FOREIGN KEY (ClienteID) REFERENCES $TABLE_REGISTRO(ClienteID)
            );
        """
        const val CREATE_TABLE_ARCHIVOS_GUARDADOS = """
            CREATE TABLE $TABLE_ARCHIVOS_GUARDADOS (
                Id INTEGER PRIMARY KEY AUTOINCREMENT,
                ClienteID INTEGER,
                Datos_Archivo BLOB,
                Nombre_Archivo TEXT,
                Tipo_Archivo TEXT,
                Fecha_Creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY (ClienteID) REFERENCES $TABLE_REGISTRO(ClienteID)
            );
        """
        const val CREATE_TABLE_REPORTES = """
            CREATE TABLE $TABLE_REPORTES (
                Id INTEGER PRIMARY KEY AUTOINCREMENT,
                ClienteID INTEGER,
                Archivo_Adjunto BLOB,
                Fecha_Ingreso TEXT,
                FOREIGN KEY (ClienteID) REFERENCES $TABLE_REGISTRO(ClienteID)
            );
        """
    }
    // *** MÉTODOS CREATE ***

    fun agregarUsuario(nombre: String, apellido: String, celular: String, correo: String, direccion: String, usuario: String, contrasena: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("Nombre", nombre)
            put("Apellido", apellido)
            put("Num_Celular", celular)
            put("Correo", correo)
            put("Direccion", direccion)
            put("Nombre_Usuario", usuario)
            put("Contraseña", contrasena)
        }
        return db.insert(TABLE_REGISTRO, null, values)
    }

    fun agregarInicioSesion(usuario: String, contrasena: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("Usuario", usuario)
            put("Contraseña", contrasena)
        }
        return db.insert(TABLE_INICIO_SESION, null, values)
    }

    fun agregarMovimiento(cantidad: Int, tipo: String, clienteID: Int, categoria: String, fecha: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("Cantidad", cantidad)
            put("Tipo_Movimiento", tipo)
            put("ClienteID", clienteID)
            put("Categoria", categoria)
            put("Fecha_Movimiento", fecha)
        }
        return db.insert(TABLE_MOVIMIENTOS, null, values)
    }

    fun agregarArchivoGuardado(clienteID: Int, datosArchivo: ByteArray, nombreArchivo: String, tipoArchivo: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ClienteID", clienteID)
            put("Datos_Archivo", datosArchivo)
            put("Nombre_Archivo", nombreArchivo)
            put("Tipo_Archivo", tipoArchivo)
        }
        return db.insert(TABLE_ARCHIVOS_GUARDADOS, null, values)
    }
    
    fun agregarReporte(clienteID: Int, archivoAdjunto: ByteArray, fechaIngreso: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("ClienteID", clienteID)
            put("Archivo_Adjunto", archivoAdjunto)
            put("Fecha_Ingreso", fechaIngreso)
        }
        return db.insert(TABLE_REPORTES, null, values)
    }

    // *** MÉTODOS READ ***

    fun obtenerUsuarios(): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.query(TABLE_REGISTRO, null, null, null, null, null, null)
        val usuarios = mutableListOf<Map<String, String>>()
        while (cursor.moveToNext()) {
            val usuario = mapOf(
                "Nombre" to cursor.getString(cursor.getColumnIndexOrThrow("Nombre")),
                "Apellido" to cursor.getString(cursor.getColumnIndexOrThrow("Apellido")),
                "Num_Celular" to cursor.getString(cursor.getColumnIndexOrThrow("Num_Celular")),
                "Correo" to cursor.getString(cursor.getColumnIndexOrThrow("Correo")),
                "Direccion" to cursor.getString(cursor.getColumnIndexOrThrow("Direccion"))
            )
            usuarios.add(usuario)
        }
        cursor.close()
        return usuarios
    }

    fun obtenerIniciosSesion(): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.query(TABLE_INICIO_SESION, null, null, null, null, null, null)
        val iniciosSesion = mutableListOf<Map<String, String>>()
        while (cursor.moveToNext()) {
            val inicioSesion = mapOf(
                "Usuario" to cursor.getString(cursor.getColumnIndexOrThrow("Usuario")),
                "Contraseña" to cursor.getString(cursor.getColumnIndexOrThrow("Contraseña"))
            )
            iniciosSesion.add(inicioSesion)
        }
        cursor.close()
        return iniciosSesion
    }

    fun obtenerMovimientos(): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.query(TABLE_MOVIMIENTOS, null, null, null, null, null, null)
        val movimientos = mutableListOf<Map<String, String>>()
        while (cursor.moveToNext()) {
            val movimiento = mapOf(
                "Cantidad" to cursor.getString(cursor.getColumnIndexOrThrow("Cantidad")),
                "Tipo_Movimiento" to cursor.getString(cursor.getColumnIndexOrThrow("Tipo_Movimiento")),
                "ClienteID" to cursor.getString(cursor.getColumnIndexOrThrow("ClienteID")),
                "Categoria" to cursor.getString(cursor.getColumnIndexOrThrow("Categoria")),
                "Fecha_Movimiento" to cursor.getString(cursor.getColumnIndexOrThrow("Fecha_Movimiento"))
            )
            movimientos.add(movimiento)
        }
        cursor.close()
        return movimientos
    }

    fun obtenerArchivosGuardados(): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.query(TABLE_ARCHIVOS_GUARDADOS, null, null, null, null, null, null)
        val archivos = mutableListOf<Map<String, String>>()
        while (cursor.moveToNext()) {
            val archivo = mapOf(
                "ClienteID" to cursor.getString(cursor.getColumnIndexOrThrow("ClienteID")),
                "Nombre_Archivo" to cursor.getString(cursor.getColumnIndexOrThrow("Nombre_Archivo")),
                "Tipo_Archivo" to cursor.getString(cursor.getColumnIndexOrThrow("Tipo_Archivo")),
                "Fecha_Creacion" to cursor.getString(cursor.getColumnIndexOrThrow("Fecha_Creacion"))
            )
            archivos.add(archivo)
        }
        cursor.close()
        return archivos
    }

    fun obtenerReportes(): List<Map<String, String>> {
        val db = readableDatabase
        val cursor = db.query(TABLE_REPORTES, null, null, null, null, null, null)
        val reportes = mutableListOf<Map<String, String>>()
        while (cursor.moveToNext()) {
            val reporte = mapOf(
                "ClienteID" to cursor.getString(cursor.getColumnIndexOrThrow("ClienteID")),
                "Fecha_Ingreso" to cursor.getString(cursor.getColumnIndexOrThrow("Fecha_Ingreso"))
            )
            reportes.add(reporte)
        }
        cursor.close()
        return reportes
    }

    // *** MÉTODOS DELETE ***

    fun eliminarUsuario(clienteID: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_REGISTRO, "ClienteID=?", arrayOf(clienteID.toString()))
    }

    fun eliminarMovimiento(idMovimiento: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_MOVIMIENTOS, "Id_Movimiento=?", arrayOf(idMovimiento.toString()))
    }

    fun eliminarArchivoGuardado(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_ARCHIVOS_GUARDADOS, "Id=?", arrayOf(id.toString()))
    }

    fun eliminarReporte(id: Int): Int {
        val db = writableDatabase
        return db.delete(TABLE_REPORTES, "Id=?", arrayOf(id.toString()))
    }
}
