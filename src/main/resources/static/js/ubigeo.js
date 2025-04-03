document.addEventListener("DOMContentLoaded", function () {
    const departamentoSelect = document.getElementById("departamento");
    const provinciaSelect = document.getElementById("provincia");
    const distritoSelect = document.getElementById("distrito");

    const valorDepartamento = departamentoSelect.getAttribute("data-valor");
    const valorProvincia = provinciaSelect.getAttribute("data-valor");
    const valorDistrito = distritoSelect.getAttribute("data-valor");

    let departamentoIdSeleccionado = null;
    let provinciaIdSeleccionada = null;

    // 1. Cargar departamentos
    fetch('/data/departamentos.json')
        .then(res => res.json())
        .then(departamentos => {
            departamentoSelect.innerHTML = "<option value=''>Seleccione</option>";

            departamentos.forEach(dep => {
                const option = document.createElement("option");
                option.value = dep.id;
                option.text = dep.name;

                if (valorDepartamento && dep.name === valorDepartamento) {
                    option.selected = true;
                    departamentoIdSeleccionado = dep.id;
                }

                departamentoSelect.appendChild(option);
            });

            if (departamentoIdSeleccionado) {
                cargarProvincias(departamentoIdSeleccionado);
            }
        });

    // 2. Evento al cambiar departamento
    departamentoSelect.addEventListener("change", function () {
        const depId = this.value;
        provinciaSelect.innerHTML = "<option value=''>Seleccione</option>";
        distritoSelect.innerHTML = "<option value=''>Seleccione</option>";

        if (depId) {
            cargarProvincias(depId);
        }
    });

    // 3. Evento al cambiar provincia
    provinciaSelect.addEventListener("change", function () {
        const depId = departamentoSelect.value;
        const provId = this.value;

        distritoSelect.innerHTML = "<option value=''>Seleccione</option>";

        if (depId && provId) {
            cargarDistritos(depId, provId);
        }
    });

    // 4. Cargar provincias
    function cargarProvincias(depId) {
        fetch('/data/provincias.json')
            .then(res => res.json())
            .then(provincias => {
                provinciaSelect.innerHTML = "<option value=''>Seleccione</option>";

                provincias.filter(p => p.department_id === depId)
                    .forEach(p => {
                        const option = document.createElement("option");
                        option.value = p.id;
                        option.text = p.name;

                        if (valorProvincia && p.name === valorProvincia) {
                            option.selected = true;
                            provinciaIdSeleccionada = p.id;
                        }

                        provinciaSelect.appendChild(option);
                    });

                if (provinciaIdSeleccionada) {
                    cargarDistritos(depId, provinciaIdSeleccionada);
                }
            });
    }

    // 5. Cargar distritos
    function cargarDistritos(depId, provId) {
        fetch('/data/distritos.json')
            .then(res => res.json())
            .then(distritos => {
                distritoSelect.innerHTML = "<option value=''>Seleccione</option>";

                distritos.filter(d => d.department_id === depId && d.province_id === provId)
                    .forEach(d => {
                        const option = document.createElement("option");
                        option.value = d.name;
                        option.text = d.name;

                        if (valorDistrito && d.name === valorDistrito) {
                            option.selected = true;
                        }

                        distritoSelect.appendChild(option);
                    });
            });
    }
});
