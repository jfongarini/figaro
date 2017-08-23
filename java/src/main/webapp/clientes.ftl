<!Doctype html>
<html>
<head> 
	<title>CLIENTES</title>
  <#include "/common/head.ftl"> 
</head>
<body ng-app=figaro ng-controller=clientesController>
  <div id=content >
    <#include "/common/menu.ftl">
    <div class="banner">
        <label class=date ><i class=material-icons onclick="openModal();" >person_add</i></label>
        <label class=date ><i class=material-icons >more_vert</i></label>
        <label class=date ><i class=material-icons >search</i></label><input class=input-date type="text" placeholder="Buscar..."></input>
    </div>
    <table id=table>
      <thead>
        <tr>
            <th>NOMBRE</th>
            <th>APELLIDO</th>
            <th>EMAIL</th>
            <th>TELEFONO</th>
            <th>FECHA ALTA</th>
            <th>ULTIMA VISITA</th>
        </tr>
      </thead>
      <tbody>
        <tr class=data-row ng-repeat="cliente in clientes">
          <td>{{cliente.nombre}}</td>
          <td>{{cliente.apellido}}</td>
          <td>{{cliente.email}}</td>
          <td>{{cliente.telefono}}</td>
          <td>{{cliente.fechaIngreso}}</td>
          <td>{{cliente.ultimaVisita}}</td>
        </tr>
      </tbody>
    </table>
  </div>
  <div id=modal class=modal>
    <div class=modal-content>
      <form ng-submit="submitForm()">
        <div class=form-field><label>NOMBRE:</label><input tipe="text" ng-model="nuevoCliente.nombre"></input></div>
        <div class=form-field><label>APELLIDO:</label><input tipe="text" ng-model="nuevoCliente.apellido"></input></div>
        <div class=form-field><label>SEXO:</label><input tipe="text" ng-model="nuevoCliente.sexo"></input></div>
        <div class=form-field><label>TELEFONO:</label><input tipe="number" ng-model="nuevoCliente.telefono"></input></div>
        <div class=form-field><label>EMAIL:</label><input tipe="email" ng-model="nuevoCliente.email"></input></div>
        <div class=form-field><label>CIUDAD:</label><input tipe="text" ng-model="nuevoCliente.dirCiudad"></input></div>
        <div class=form-field><label>CALLE:</label><input tipe="text" ng-model="nuevoCliente.dirCalle"></input></div>
        <div class=form-field><label>NUMERO:</label><input tipe="number" ng-model="nuevoCliente.dirNumeroCalle"></input></div>
        <div class=form-field><label>PISO:</label><input tipe="number" ng-model="nuevoCliente.dirPiso"></input></div>
        <div class=form-field><label>DPTO:</label><input tipe="text" ng-model="nuevoCliente.dirDpto"></input></div>
        <button type="submit">ACEPTAR</button>
      </form>
    </div>
  </div>
  <#include "/common/js.ftl">
  <script src="js/clientesController.js" ></script>
</body>
</html>