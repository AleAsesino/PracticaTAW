package com.universidad.service; // Define el paquete al que pertenece esta interfaz

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto

import java.time.LocalDate;
import java.util.List; // Importa la interfaz List para manejar listas

public interface IEstudianteService { // Define la interfaz IEstudianteService
    
    List<EstudianteDTO> obtenerTodosLosEstudiantes(); // Método para obtener una lista de todos los EstudianteDTO
    List<EstudianteDTO> eliminarEstudiante(Long id);
    Boolean eliminarEstudianteV2(Long id);
    EstudianteDTO verificarExistencia(Long id);
    Boolean actualizarEstudiante(Long id, String nombre, String apellido, String gmail, LocalDate fechaNaci, String numIns);
    void crear(String nombre, String apellido, String gmail, LocalDate fechaNaci, String numIns);
}