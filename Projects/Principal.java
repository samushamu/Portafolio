package principal;

import java.io.*;
import java.util.Scanner;

public class Principal {

    private static final String ARCHIVO_DATOS = "usuarios_tareas.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario[] usuarios = new Usuario[10];
        int usuarioActual = -1;

        cargarUsuariosYtareas(usuarios);

        boolean salir = false;

        while (!salir) {
            System.out.println("----- Gestor de Tareas -----");
            System.out.println("1. Crear usuario");
            System.out.println("2. Editar usuario");
            System.out.println("3. Seleccionar usuario");
            System.out.println("4. Listar usuarios");
            System.out.println("5. Guardar y salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            limpiarConsola();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce el nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Introduce el email del usuario: ");
                    String email = scanner.nextLine();

                    for (int i = 0; i < usuarios.length; i++) {
                        if (usuarios[i] == null) {
                            usuarios[i] = new Usuario(nombre, email);
                            System.out.println("Usuario creado con éxito.");
                            break;
                        }
                    }
                    break;

                case 2:
                    if (usuarioActual == -1) {
                        System.out.println("Primero debes seleccionar un usuario.");
                        break;
                    }
                    System.out.print("Introduce el nuevo nombre del usuario: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Introduce el nuevo email del usuario: ");
                    String nuevoEmail = scanner.nextLine();
                    usuarios[usuarioActual].setNombre(nuevoNombre);
                    usuarios[usuarioActual].setEmail(nuevoEmail);
                    System.out.println("Usuario actualizado con éxito.");
                    break;

                case 3:
                    listarUsuarios(usuarios);
                    System.out.print("Selecciona el número del usuario: ");
                    int seleccion = scanner.nextInt();
                    scanner.nextLine();
                    if (seleccion > 0 && seleccion <= usuarios.length && usuarios[seleccion - 1] != null) {
                        usuarioActual = seleccion - 1;
                        menuTareas(scanner, usuarios[usuarioActual]);
                    } else {
                        System.out.println("Usuario no válido.");
                    }
                    break;

                case 4:
                    listarUsuarios(usuarios);
                    break;

                case 5:
                    salir = true;
                    guardarUsuariosYtareas(usuarios);
                    System.out.println("Guardando y saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        scanner.close();
    }

    private static void listarUsuarios(Usuario[] usuarios) {
        System.out.println("----- Lista de Usuarios -----");
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null) {
                System.out.println((i + 1) + ". " + usuarios[i].getNombre() + " - " + usuarios[i].getEmail());
            }
        }
    }

    private static void menuTareas(Scanner scanner, Usuario usuario) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n----- Gestor de Tareas para " + usuario.getNombre() + " -----");
            System.out.println("1. Crear tarea");
            System.out.println("2. Editar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Listar tareas");
            System.out.println("5. Volver");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            limpiarConsola();

            switch (opcion) {
                case 1:
                    crearTarea(scanner, usuario);
                    break;
                case 2:
                    editarTarea(scanner, usuario);
                    break;
                case 3:
                    eliminarTarea(scanner, usuario);
                    break;
                case 4:
                    usuario.obtenerListaDeTareas();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void crearTarea(Scanner scanner, Usuario usuario) {
        System.out.print("Introduce la descripción de la tarea: ");
        String descripcion = scanner.nextLine();
        System.out.print("Introduce la fecha límite (YYYY-MM-DD): ");
        String fechaLimite = scanner.nextLine();
        System.out.print("Introduce la prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
        int prioridad = scanner.nextInt();
        scanner.nextLine();

        usuario.crearTarea(descripcion, fechaLimite, prioridad);
        System.out.println("Tarea creada con éxito.");
    }

    private static void editarTarea(Scanner scanner, Usuario usuario) {
        System.out.print("Introduce el número de la tarea que deseas editar: ");
        int tareaIndex = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Introduce la nueva descripción de la tarea: ");
        String nuevaDescripcion = scanner.nextLine();
        System.out.print("Introduce la nueva fecha límite (YYYY-MM-DD): ");
        String nuevaFecha = scanner.nextLine();
        System.out.print("Introduce la nueva prioridad (1 = Alta, 2 = Media, 3 = Baja): ");
        int nuevaPrioridad = scanner.nextInt();
        scanner.nextLine();

        usuario.editarTarea(tareaIndex - 1, nuevaDescripcion, nuevaFecha, nuevaPrioridad);
        System.out.println("Tarea editada con éxito.");
    }

    private static void eliminarTarea(Scanner scanner, Usuario usuario) {
        System.out.print("Introduce el número de la tarea que deseas eliminar: ");
        int tareaIndex = scanner.nextInt();
        scanner.nextLine();

        usuario.eliminarTarea(tareaIndex - 1);
        System.out.println("Tarea eliminada con éxito.");
    }

    public static void limpiarConsola() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    // Guardar usuarios y sus tareas en un archivo de texto
    private static void guardarUsuariosYtareas(Usuario[] usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_DATOS))) {
            for (Usuario usuario : usuarios) {
                if (usuario != null) {
                    writer.write(usuario.datosParaGuardar());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    private static void cargarUsuariosYtareas(Usuario[] usuarios) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_DATOS))) {
            String linea;
            int index = 0;
            while ((linea = reader.readLine()) != null && index < usuarios.length) {
                String[] datosUsuario = linea.split(";");
                String nombre = datosUsuario[0];
                String email = datosUsuario[1];
                Usuario usuario = new Usuario(nombre, email);

                int numTareas = Integer.parseInt(reader.readLine()); 
                for (int i = 0; i < numTareas; i++) {
                    String[] datosTarea = reader.readLine().split(";");
                    String descripcion = datosTarea[0];
                    String fechaLimite = datosTarea[1];
                    int prioridad = Integer.parseInt(datosTarea[2]);
                    usuario.crearTarea(descripcion, fechaLimite, prioridad);
                }

                usuarios[index++] = usuario;
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}