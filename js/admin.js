document.addEventListener('DOMContentLoaded', () => {
    inicializarCatalogoLocalStorage();
    renderizarTablaAdmin();

    const formAdmin = document.getElementById('form-admin-producto');
    if (formAdmin) {
        formAdmin.addEventListener('submit', guardarProducto);
    }
});

// Inicializar el almacenamiento si aún no existe
function inicializarCatalogoLocalStorage() {
    if (!localStorage.getItem('catalogo_productos')) {
        localStorage.setItem('catalogo_productos', JSON.stringify(productos));
    }
}

// Obtener la lista actual de productos
function obtenerCatalogo() {
    inicializarCatalogoLocalStorage();
    return JSON.parse(localStorage.getItem('catalogo_productos'));
}

// Guardar la lista actualizada en LocalStorage
function guardarCatalogo(lista) {
    localStorage.setItem('catalogo_productos', JSON.stringify(lista));
    renderizarTablaAdmin();
}

// Renderizar la tabla en admin-productos.html
function renderizarTablaAdmin() {
    const tbody = document.getElementById('tabla-productos-body');
    if (!tbody) return;

    const lista = obtenerCatalogo();
    tbody.innerHTML = '';

    if (lista.length === 0) {
        tbody.innerHTML = '<tr><td colspan="6">No hay productos registrados.</td></tr>';
        return;
    }

    lista.forEach(juego => {
        const fila = document.createElement('tr');

        fila.innerHTML = `
            <td>${juego.id}</td>
            <td><img src="${juego.imagen}" alt="${juego.nombre}" class="thumb-admin"></td>
            <td><strong>${juego.nombre}</strong></td>
            <td>${juego.plataforma}</td>
            <td>$${juego.precio.toLocaleString('es-CL')}</td>
            <td>
                <button class="btn-accion btn-editar" onclick="prepararEdicion(${juego.id})">Editar</button>
                <button class="btn-accion btn-eliminar-admin" onclick="eliminarProducto(${juego.id})">Eliminar</button>
            </td>
        `;

        tbody.appendChild(fila);
    });
}

// Guardar o Actualizar un Producto
function guardarProducto(e) {
    e.preventDefault();
    let esValido = true;

    const idInput = document.getElementById('prod-id').value;
    const nombre = document.getElementById('prod-nombre').value.trim();
    const plataforma = document.getElementById('prod-plataforma').value;
    const precio = document.getElementById('prod-precio').value.trim();
    const imagen = document.getElementById('prod-imagen').value.trim();
    const descripcion = document.getElementById('prod-descripcion').value.trim();

    // Validaciones
    if (!nombre) {
        mostrarError('error-prod-nombre', 'El nombre es obligatorio.');
        esValido = false;
    } else {
        mostrarError('error-prod-nombre', '');
    }

    if (!plataforma) {
        mostrarError('error-prod-plataforma', 'Seleccione una plataforma.');
        esValido = false;
    } else {
        mostrarError('error-prod-plataforma', '');
    }

    if (!precio || isNaN(precio) || Number(precio) <= 0) {
        mostrarError('error-prod-precio', 'Ingrese un precio mayor a 0.');
        esValido = false;
    } else {
        mostrarError('error-prod-precio', '');
    }

    if (!imagen) {
        mostrarError('error-prod-imagen', 'Ingrese la URL de la imagen.');
        esValido = false;
    } else {
        mostrarError('error-prod-imagen', '');
    }

    if (!descripcion) {
        mostrarError('error-prod-descripcion', 'La descripción es obligatoria.');
        esValido = false;
    } else {
        mostrarError('error-prod-descripcion', '');
    }

    if (!esValido) return;

    let catalogo = obtenerCatalogo();

    if (idInput) {
        // EDICIÓN
        const idNum = parseInt(idInput);
        const index = catalogo.findIndex(p => p.id === idNum);
        if (index !== -1) {
            catalogo[index] = {
                id: idNum,
                nombre,
                plataforma,
                precio: parseFloat(precio),
                imagen,
                descripcion
            };
            alert('Producto actualizado con éxito');
        }
    } else {
        // CREACIÓN DE NUEVO PRODUCTO
        const nuevoId = catalogo.length > 0 ? Math.max(...catalogo.map(p => p.id)) + 1 : 1;
        const nuevoProducto = {
            id: nuevoId,
            nombre,
            plataforma,
            precio: parseFloat(precio),
            imagen,
            descripcion
        };
        catalogo.push(nuevoProducto);
        alert('Producto agregado con éxito');
    }

    guardarCatalogo(catalogo);
    limpiarFormularioAdmin();
}

// Cargar datos en el formulario para editar
function prepararEdicion(id) {
    const catalogo = obtenerCatalogo();
    const juego = catalogo.find(p => p.id === id);

    if (!juego) return;

    document.getElementById('prod-id').value = juego.id;
    document.getElementById('prod-nombre').value = juego.nombre;
    document.getElementById('prod-plataforma').value = juego.plataforma;
    document.getElementById('prod-precio').value = juego.precio;
    document.getElementById('prod-imagen').value = juego.imagen;
    document.getElementById('prod-descripcion').value = juego.descripcion;

    document.getElementById('form-titulo').innerText = 'Editar Producto (ID: ' + juego.id + ')';
    document.getElementById('btn-guardar-prod').innerText = 'Actualizar Producto';
    document.getElementById('btn-cancelar-prod').style.display = 'inline-block';
}

// Eliminar un Producto
function eliminarProducto(id) {
    if (confirm('¿Está seguro de que desea eliminar este producto?')) {
        let catalogo = obtenerCatalogo();
        catalogo = catalogo.filter(p => p.id !== id);
        guardarCatalogo(catalogo);
        alert('Producto eliminado');
    }
}

// Limpiar formulario y reiniciar estado
function limpiarFormularioAdmin() {
    document.getElementById('form-admin-producto').reset();
    document.getElementById('prod-id').value = '';
    document.getElementById('form-titulo').innerText = 'Agregar Nuevo Producto';
    document.getElementById('btn-guardar-prod').innerText = 'Guardar Producto';
    document.getElementById('btn-cancelar-prod').style.display = 'none';

    ['error-prod-nombre', 'error-prod-plataforma', 'error-prod-precio', 'error-prod-imagen', 'error-prod-descripcion'].forEach(id => {
        mostrarError(id, '');
    });
}

function mostrarError(elementId, mensaje) {
    const errorSpan = document.getElementById(elementId);
    if (errorSpan) errorSpan.innerText = mensaje;
}