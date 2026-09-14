document.addEventListener('DOMContentLoaded', () => {
    actualizarContadorCarrito();
    verificarEstadoSesion();

    // Renderizar grilla de productos si estamos en index.html o productos.html
    if (document.getElementById('contenedor-productos')) {
        mostrarProductos();
    }

    // Renderizar lista de compras si estamos en carrito.html
    if (document.getElementById('contenedor-carrito')) {
        renderizarCarrito();
    }
});

// --- GESTIÓN DEL CARRITO EN LOCALSTORAGE ---
function obtenerCarrito() {
    const carritoGuardado = localStorage.getItem('carrito');
    return carritoGuardado ? JSON.parse(carritoGuardado) : [];
}

function guardarCarrito(carrito) {
    localStorage.setItem('carrito', JSON.stringify(carrito));
    actualizarContadorCarrito();
}

function actualizarContadorCarrito() {
    const carrito = obtenerCarrito();
    const totalItems = carrito.reduce((sum, item) => sum + item.cantidad, 0);
    const contador = document.getElementById('cart-count');
    if (contador) contador.innerText = totalItems;
}

// --- RENDERIZADO DE PRODUCTOS ---
function mostrarProductos() {
    const contenedor = document.getElementById('contenedor-productos');
    if (!contenedor) return;

    const catalogoGuardado = localStorage.getItem('catalogo_productos');
    const listaJuegos = catalogoGuardado ? JSON.parse(catalogoGuardado) : productos;

    contenedor.innerHTML = '';

    listaJuegos.forEach(juego => {
        const tarjeta = document.createElement('div');
        tarjeta.classList.add('card-producto');

        tarjeta.innerHTML = `
            <img src="${juego.imagen}" alt="${juego.nombre}">
            <h4>${juego.nombre}</h4>
            <p class="plataforma">${juego.plataforma}</p>
            <p class="precio">$${juego.precio.toLocaleString('es-CL')}</p>
            <button class="btn-primary" onclick="agregarAlCarrito(${juego.id})">Añadir al carrito</button>
        `;

        contenedor.appendChild(tarjeta);
    });
}

function agregarAlCarrito(id) {
    let carrito = obtenerCarrito();
    const itemExistente = carrito.find(item => item.id === id);

    if (itemExistente) {
        itemExistente.cantidad++;
    } else {
        const catalogoGuardado = localStorage.getItem('catalogo_productos');
        const listaJuegos = catalogoGuardado ? JSON.parse(catalogoGuardado) : productos;
        const producto = listaJuegos.find(p => p.id === id);
        
        if (producto) {
            carrito.push({ ...producto, cantidad: 1 });
        }
    }

    guardarCarrito(carrito);
    alert('Producto añadido al carrito');
}

function renderizarCarrito() {
    const contenedor = document.getElementById('contenedor-carrito');
    const totalElemento = document.getElementById('total-compra');
    const carrito = obtenerCarrito();

    if (!contenedor) return;

    contenedor.innerHTML = '';

    if (carrito.length === 0) {
        contenedor.innerHTML = '<p>El carrito está vacío.</p>';
        if (totalElemento) totalElemento.innerText = '$0';
        return;
    }

    let totalCalculado = 0;

    carrito.forEach(item => {
        const subtotal = item.precio * item.cantidad;
        totalCalculado += subtotal;

        const elemento = document.createElement('div');
        elemento.classList.add('item-carrito');

        elemento.innerHTML = `
            <img src="${item.imagen}" alt="${item.nombre}">
            <div class="info-item">
                <h4>${item.nombre}</h4>
                <p class="precio">$${item.precio.toLocaleString('es-CL')}</p>
            </div>
            <div class="controles-cantidad">
                <button onclick="cambiarCantidad(${item.id}, -1)">-</button>
                <span>${item.cantidad}</span>
                <button onclick="cambiarCantidad(${item.id}, 1)">+</button>
            </div>
            <button class="btn-eliminar" onclick="eliminarDelCarrito(${item.id})">🗑️</button>
        `;

        contenedor.appendChild(elemento);
    });

    if (totalElemento) {
        totalElemento.innerText = `$${totalCalculado.toLocaleString('es-CL')}`;
    }
}

function cambiarCantidad(id, cambio) {
    let carrito = obtenerCarrito();
    const item = carrito.find(p => p.id === id);

    if (item) {
        item.cantidad += cambio;
        if (item.cantidad <= 0) {
            carrito = carrito.filter(p => p.id !== id);
        }
        guardarCarrito(carrito);
        renderizarCarrito();
    }
}

function eliminarDelCarrito(id) {
    let carrito = obtenerCarrito();
    carrito = carrito.filter(p => p.id !== id);
    guardarCarrito(carrito);
    renderizarCarrito();
}

function procesarPago() {
    const carrito = obtenerCarrito();
    if (carrito.length === 0) {
        alert('Tu carrito está vacío');
        return;
    }
    alert('¡Gracias por tu compra!');
    localStorage.removeItem('carrito');
    actualizarContadorCarrito();
    renderizarCarrito();
}

// --- LÓGICA DEL CARRUSEL ---
let slideActual = 0;

function mostrarSlide(index) {
    const slides = document.querySelectorAll('.carousel-slide');
    if (slides.length === 0) return;

    if (index >= slides.length) slideActual = 0;
    else if (index < 0) slideActual = slides.length - 1;
    else slideActual = index;

    slides.forEach((slide, i) => {
        slide.classList.toggle('active', i === slideActual);
    });
}

function cambiarSlide(direccion) {
    mostrarSlide(slideActual + direccion);
}

setInterval(() => {
    if (document.querySelectorAll('.carousel-slide').length > 0) {
        cambiarSlide(1);
    }
}, 4000);

// --- REGISTRO Y AUTENTICACIÓN DE USUARIOS ---

function obtenerUsuariosRegistrados() {
    const usuarios = localStorage.getItem('usuarios_gamerzone');
    return usuarios ? JSON.parse(usuarios) : [];
}

// 1. Lógica de Registro con persistencia
const formRegistro = document.getElementById('form-registro');
if (formRegistro) {
    formRegistro.addEventListener('submit', function(e) {
        e.preventDefault();
        let esValido = true;

        document.querySelectorAll('.error-msg').forEach(span => span.innerText = '');

        const nombre = document.getElementById('reg-nombre').value.trim();
        const rut = document.getElementById('reg-rut').value.trim();
        const email = document.getElementById('reg-email').value.trim().toLowerCase();
        const pass = document.getElementById('reg-pass').value.trim();
        const pass2 = document.getElementById('reg-pass2').value.trim();

        if (nombre.length < 3) {
            document.getElementById('error-nombre').innerText = 'El nombre debe tener al menos 3 caracteres.';
            esValido = false;
        }

        const rutRegex = /^[0-9]+-[0-9kK]{1}$/;
        if (!rutRegex.test(rut)) {
            document.getElementById('error-rut').innerText = 'Formato de RUT inválido (Ej: 12345678-9).';
            esValido = false;
        }

        if (!email.includes('@') || !email.includes('.')) {
            document.getElementById('error-email').innerText = 'Ingresa un correo electrónico válido.';
            esValido = false;
        }

        // Verificar si el correo ya existe en LocalStorage
        const usuariosGuardados = obtenerUsuariosRegistrados();
        const correoExistente = usuariosGuardados.find(u => u.email === email);

        if (correoExistente) {
            document.getElementById('error-email').innerText = 'Este correo electrónico ya está registrado.';
            esValido = false;
        }

        if (pass.length < 6) {
            document.getElementById('error-pass').innerText = 'La contraseña debe tener al menos 6 caracteres.';
            esValido = false;
        }

        if (pass !== pass2) {
            document.getElementById('error-pass2').innerText = 'Las contraseñas no coinciden.';
            esValido = false;
        }

        if (esValido) {
            const nuevoUsuario = { nombre, rut, email, pass };
            usuariosGuardados.push(nuevoUsuario);
            localStorage.setItem('usuarios_gamerzone', JSON.stringify(usuariosGuardados));

            alert('¡Usuario registrado con éxito! Ahora puedes iniciar sesión.');
            formRegistro.reset();
            window.location.href = 'login.html';
        }
    });
}

// 2. Lógica de Login (Autenticación)
const formLogin = document.getElementById('form-login');
if (formLogin) {
    formLogin.addEventListener('submit', function(e) {
        e.preventDefault();

        const emailInput = document.getElementById('login-email').value.trim().toLowerCase();
        const passInput = document.getElementById('login-pass').value.trim();

        const errorEmail = document.getElementById('error-login-email');
        const errorPass = document.getElementById('error-login-pass');

        errorEmail.innerText = '';
        errorPass.innerText = '';

        if (!emailInput) {
            errorEmail.innerText = 'Por favor, ingresa tu correo.';
            return;
        }

        if (!passInput) {
            errorPass.innerText = 'Por favor, ingresa tu contraseña.';
            return;
        }

        const usuarios = obtenerUsuariosRegistrados();
        const usuarioEncontrado = usuarios.find(u => u.email === emailInput);

        // Validación 1: El correo no existe
        if (!usuarioEncontrado) {
            errorEmail.innerText = 'El correo electrónico ingresado no está registrado.';
            return;
        }

        // Validación 2: La contraseña no coincide
        if (usuarioEncontrado.pass !== passInput) {
            errorPass.innerText = 'Contraseña incorrecta. Inténtalo de nuevo.';
            return;
        }

        // Autenticación Exitosa: Guardar sesión activa
        localStorage.setItem('usuario_activo', JSON.stringify(usuarioEncontrado));
        alert(`¡Bienvenido de nuevo, ${usuarioEncontrado.nombre}!`);
        window.location.href = 'index.html';
    });
}

// 3. Mostrar nombre del usuario activo en el Header
function verificarEstadoSesion() {
    const usuarioActivo = localStorage.getItem('usuario_activo');
    const userActions = document.querySelector('.user-actions');

    if (usuarioActivo && userActions) {
        const usuario = JSON.parse(usuarioActivo);
        const carritoBtn = userActions.querySelector('.cart-icon').outerHTML;

        userActions.innerHTML = `
            <span style="color: #60a5fa; font-weight: 600;">Hola, ${usuario.nombre}</span> | 
            <a href="#" onclick="cerrarSesion()">Cerrar sesión</a>
            ${carritoBtn}
        `;
    }
}

function cerrarSesion() {
    localStorage.removeItem('usuario_activo');
    alert('Has cerrado sesión.');
    window.location.reload();
}