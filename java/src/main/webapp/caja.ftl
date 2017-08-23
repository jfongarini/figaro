<!Doctype html>
<html>
<head> 
  <title>CAJA</title>
  <#include "/common/head.ftl">    
</head>
<body>
  <div id=content>
  <#include "/common/menu.ftl">
  <div class=banner>
      <label class=date ><i class=material-icons >account_balance</i> DIA </label>
      <label class=date ><i class=material-icons >more_vert</i></label>
      <label class=date ><i class=material-icons >account_balance</i> SEMANA </label>
      <label class=date ><i class=material-icons >more_vert</i></label>
      <label class=date ><i class=material-icons >account_balance</i> MES </label>
      <label class=date > SEMANA DEL 17/08/17 AL 24/08/17</label>
  </div>
  <table id=table>
    <thead>
      <tr>
          <th>FECHA</th>
          <th>CATEGORIA</th>
          <th>MONTO</th>
          <th>DETALLE</th>              
      </tr>
    </thead>
    <tbody>
      <tr> 
        <td>17/08/17</td>
        <td>PROVEEDORES</td>
        <td>$400,00</td>
        <td>CREMA PARA EL PELO</td>            
      </tr>
    </tbody>
  </table>
  <div class=today>
      <label class=date ><i class=material-icons >account_balance_wallet</i> CAJA: $3465,00</label>
  </div>
</div>
<#include "/common/javascript.ftl">
</body>
</html>