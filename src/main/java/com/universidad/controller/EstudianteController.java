package com.universidad.controller; // Define el paquete al que pertenece esta clase

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.model.Estudiante;
import com.universidad.service.IEstudianteService; // Importa la interfaz IEstudianteService del paquete service

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity de Spring para manejar respuestas HTTP
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring para controladores web

import java.util.List; // Importa la interfaz List para manejar listas

@RestController // Anotación que indica que esta clase es un controlador REST de Spring
@RequestMapping("/api") // Define la ruta base para las solicitudes HTTP a este controlador
public class EstudianteController { // Define la clase EstudianteController

    private final IEstudianteService estudianteService; // Declara una variable final para el servicio de estudiantes

    @Autowired // Anotación que indica que el constructor debe ser usado para inyección de dependencias
    public EstudianteController(IEstudianteService estudianteService) { // Constructor que recibe el servicio de estudiantes
        this.estudianteService = estudianteService; // Asigna el servicio de estudiantes a la variable de instancia
    }

    @GetMapping("/estudiantes") // Anotación que indica que este método maneja solicitudes GET
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() { // Método para obtener una lista de todos los EstudianteDTO
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes(); // Llama al servicio para obtener todos los estudiantes
        return ResponseEntity.ok(estudiantes); // Retorna una respuesta HTTP 200 OK con la lista de estudiantes
    }
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<List<EstudianteDTO>> eliminarEstudianteX(@PathVariable Long id){
        List<EstudianteDTO> estudiantes = estudianteService.eliminarEstudiante(id);
        return ResponseEntity.ok(estudiantes);
    }
    @GetMapping("/eliminarV2/{id}")
    public ResponseEntity<Void> eliminarEstudianteV2(@PathVariable Long id){
        boolean eliminado = estudianteService.eliminarEstudianteV2(id);
        if(!eliminado){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/verificar/{id}")
    public ResponseEntity<?> verificarExistencia(@PathVariable Long id){
        EstudianteDTO elem = estudianteService.verificarExistencia(id);
        if(elem == null){
            return ResponseEntity.noContent().build();
        }
            //mostrar en json el objeto EstudianteDTO
        return ResponseEntity.ok(elem);

    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudiante){

        if(estudianteService.actualizarEstudiante(estudiante.getId(), estudiante.getNombre(), estudiante.getApellido(), estudiante.getEmail(), estudiante.getFechaNacimiento(), estudiante.getNumeroInscripcion())){

            return ResponseEntity.ok(estudianteService.verificarExistencia(id));
        }else{

            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<EstudianteDTO> crear(@RequestBody EstudianteDTO estudiante){
        estudianteService.crear(estudiante.getNombre(), estudiante.getApellido(), estudiante.getEmail(), estudiante.getFechaNacimiento(),estudiante.getNumeroInscripcion());
        return ResponseEntity.status(HttpStatus.CREATED).body(estudiante);
    }

}