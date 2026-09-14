// Arreglo de datos dinámico para Regiones y Comunas
const regionesYComunas = [
    {
        region: "Región Metropolitana de Santiago",
        comunas: ["Santiago", "Providencia", "Las Condes", "Maipú", "Puente Alto"]
    },
    {
        region: "Región de Valparaíso",
        comunas: ["Valparaíso", "Viña del Mar", "Quilpué", "Villa Alemana"]
    },
    {
        region: "Región de la Araucanía",
        comunas: ["Temuco", "Padre Las Casas", "Villarrica", "Pucón"]
    },
    {
        region: "Región de Ñuble",
        comunas: ["Linares", "Longaví", "Concepción", "Chillán"]
    }
];

document.addEventListener('DOMContentLoaded', () => {
    // Inicializar selectores dinámicos
    inicializarRegiones();

    // Eventos de Formularios
    const formLogin = document.getElementById('form-login');
    if (formLogin) formLogin.addEventListener('submit', validarLogin);

    const formRegistro = document.getElementById('form-registro');
    if (formRegistro) formRegistro.addEventListener('submit', validarRegistro);
});

// Cargar opciones de región al select
function inicializarRegiones() {
    const selectRegion = document.getElementById('select-region');
    const selectComuna = document.getElementById('select-comuna');

    if (!selectRegion || !selectComuna) return;

    regionesYComunas.forEach(item => {
        const option = document.createElement('option');
        option.value = item.region;
        option.textContent = item.region;
        selectRegion.appendChild(option);
    });

    // Actualizar comunas cuando cambie la región seleccionada
    selectRegion.addEventListener('change', (e) => {
        const regionSeleccionada = e.target.value;
        selectComuna.innerHTML = '<option value="">- Seleccione la comuna -</option>';

        if (regionSeleccionada === "") {
            selectComuna.disabled = true;
            return;
        }

        const objetoRegion = regionesYComunas.find(r => r.region === regionSeleccionada);
        if (objetoRegion) {
            objetoRegion.comunas.forEach(comuna => {
                const option = document.createElement('option');
                option.value = comuna;
                option.textContent = comuna;
                selectComuna.appendChild(option);
            });
            selectComuna.disabled = false;
        }
    });
}

// Validar dominios autorizados de correo
function esCorreoValido(correo) {
    const regex = /^[a-zA-Z0-9._%+-]+@(duocuc\.cl|profesor\.duocuc\.cl|gmail\.com)$/;
    return regex.test(correo);
}

// Algoritmo para validar RUN Chileno
function esRunValido(run) {
    run = run.trim().toUpperCase();
    if (run.length < 7 || run.length > 9) return false;

    const cuerpo = run.slice(0, -1);
    const dvIngresado = run.slice(-1);

    if (!/^\d+$/.test(cuerpo)) return false;

    let suma = 0;
    let multiplicador = 2;

    for (let i = cuerpo.length - 1; i >= 0; i--) {
        suma += parseInt(cuerpo.charAt(i)) * multiplicador;
        multiplicador = multiplicador === 7 ? 2 : multiplicador + 1;
    }

    const dvEsperadoCalculado = 11 - (suma % 11);
    let dvEsperado = '';

    if (dvEsperadoCalculado === 11) dvEsperado = '0';
    else if (dvEsperadoCalculado === 10) dvEsperado = 'K';
    else dvEsperado = dvEsperadoCalculado.toString();

    return dvIngresado === dvEsperado;
}

// Mostrar o limpiar mensajes de error
function mostrarError(elementId, mensaje) {
    const errorSpan = document.getElementById(elementId);
    if (errorSpan) errorSpan.innerText = mensaje;
}

// Validación del formulario de Inicio de Sesión
function validarLogin(e) {
    e.preventDefault();
    let esValido = true;

    const correo = document.getElementById('login-correo').value.trim();
    const pass = document.getElementById('login-pass').value.trim();

    // Validar Correo
    if (!correo) {
        mostrarError('error-login-correo', 'El correo es requerido.');
        esValido = false;
    } else if (correo.length > 100) {
        mostrarError('error-login-correo', 'Máximo 100 caracteres.');
        esValido = false;
    } else if (!esCorreoValido(correo)) {
        mostrarError('error-login-correo', 'Solo correos con @duoc.cl, @profesor.duoc.cl y @gmail.com');
        esValido = false;
    } else {
        mostrarError('error-login-correo', '');
    }

    // Validar Contraseña
    if (!pass) {
        mostrarError('error-login-pass', 'La contraseña es requerida.');
        esValido = false;
    } else if (pass.length < 4 || pass.length > 10) {
        mostrarError('error-login-pass', 'Debe tener entre 4 y 10 caracteres.');
        esValido = false;
    } else {
        mostrarError('error-login-pass', '');
    }

    if (esValido) {
        alert('¡Inicio de sesión exitoso!');
        window.location.href = 'index.html';
    }
}

// Validación del formulario de Registro
function validarRegistro(e) {
    e.preventDefault();
    let esValido = true;

    const run = document.getElementById('reg-run').value.trim();
    const nombre = document.getElementById('reg-nombre').value.trim();
    const apellidos = document.getElementById('reg-apellidos').value.trim();
    const correo = document.getElementById('reg-correo').value.trim();
    const direccion = document.getElementById('reg-direccion').value.trim();
    const region = document.getElementById('select-region').value;
    const comuna = document.getElementById('select-comuna').value;

    // RUN
    if (!run) {
        mostrarError('error-reg-run', 'El RUN es requerido.');
        esValido = false;
    } else if (!esRunValido(run)) {
        mostrarError('error-reg-run', 'RUN inválido (Ej formato válido: 19011022K).');
        esValido = false;
    } else {
        mostrarError('error-reg-run', '');
    }

    // Nombre
    if (!nombre) {
        mostrarError('error-reg-nombre', 'El nombre es requerido.');
        esValido = false;
    } else if (nombre.length > 50) {
        mostrarError('error-reg-nombre', 'Máximo 50 caracteres.');
        esValido = false;
    } else {
        mostrarError('error-reg-nombre', '');
    }

    // Apellidos
    if (!apellidos) {
        mostrarError('error-reg-apellidos', 'Los apellidos son requeridos.');
        esValido = false;
    } else if (apellidos.length > 100) {
        mostrarError('error-reg-apellidos', 'Máximo 100 caracteres.');
        esValido = false;
    } else {
        mostrarError('error-reg-apellidos', '');
    }

    // Correo
    if (!correo) {
        mostrarError('error-reg-correo', 'El correo es requerido.');
        esValido = false;
    } else if (correo.length > 100) {
        mostrarError('error-reg-correo', 'Máximo 100 caracteres.');
        esValido = false;
    } else if (!esCorreoValido(correo)) {
        mostrarError('error-reg-correo', 'Solo correos con @duoc.cl, @profesor.duoc.cl y @gmail.com');
        esValido = false;
    } else {
        mostrarError('error-reg-correo', '');
    }

    // Dirección
    if (!direccion) {
        mostrarError('error-reg-direccion', 'La dirección es requerida.');
        esValido = false;
    } else if (direccion.length > 300) {
        mostrarError('error-reg-direccion', 'Máximo 300 caracteres.');
        esValido = false;
    } else {
        mostrarError('error-reg-direccion', '');
    }

    // Región y Comuna
    if (!region) {
        mostrarError('error-select-region', 'Debe seleccionar una región.');
        esValido = false;
    } else {
        mostrarError('error-select-region', '');
    }

    if (!comuna) {
        mostrarError('error-select-comuna', 'Debe seleccionar una comuna.');
        esValido = false;
    } else {
        mostrarError('error-select-comuna', '');
    }

    if (esValido) {
        alert('¡Usuario registrado correctamente!');
        window.location.href = 'login.html';
    }
}
// Agregar listener para el formulario de contacto al cargar
document.addEventListener('DOMContentLoaded', () => {
    const formContacto = document.getElementById('form-contacto');
    if (formContacto) formContacto.addEventListener('submit', validarContacto);
});

// Validación del Formulario de Contacto
function validarContacto(e) {
    e.preventDefault();
    let esValido = true;

    const nombre = document.getElementById('contacto-nombre').value.trim();
    const correo = document.getElementById('contacto-correo').value.trim();
    const comentario = document.getElementById('contacto-comentario').value.trim();

    // Validar Nombre
    if (!nombre) {
        mostrarError('error-contacto-nombre', 'El nombre es requerido.');
        esValido = false;
    } else if (nombre.length > 100) {
        mostrarError('error-contacto-nombre', 'Máximo 100 caracteres.');
        esValido = false;
    } else {
        mostrarError('error-contacto-nombre', '');
    }

    // Validar Correo
    if (!correo) {
        mostrarError('error-contacto-correo', 'El correo es requerido.');
        esValido = false;
    } else if (correo.length > 100) {
        mostrarError('error-contacto-correo', 'Máximo 100 caracteres.');
        esValido = false;
    } else if (!esCorreoValido(correo)) {
        mostrarError('error-contacto-correo', 'Solo correos con @duoc.cl, @profesor.duoc.cl y @gmail.com');
        esValido = false;
    } else {
        mostrarError('error-contacto-correo', '');
    }

    // Validar Comentario
    if (!comentario) {
        mostrarError('error-contacto-comentario', 'El comentario es requerido.');
        esValido = false;
    } else if (comentario.length > 500) {
        mostrarError('error-contacto-comentario', 'Máximo 500 caracteres.');
        esValido = false;
    } else {
        mostrarError('error-contacto-comentario', '');
    }

    if (esValido) {
        alert('¡Mensaje enviado con éxito!');
        document.getElementById('form-contacto').reset();
    }
}