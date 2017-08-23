<!Doctype html>
<html>
<head> 
  <title>PELUQUER&Iacute;A</title>
  <#include "/common/head.ftl">
</head>
<body>
<div id=content>
  <#include "/common/menu.ftl">
  <div class=banner>
      <label class=date ><i class=material-icons >today</i> TURNOS DEL D&Iacute;A:</label><input class=input-date type=date value=1945-10-17 name=datePicked>
  </div>
  <table id=table>
      <thead>
        <tr>
            <th></th>
            <th>HORA</th>
            <th>CLIENTE</th>
            <th>TRABAJO</th>
            <th>PRECIO</th>
            <th>PELUQUERO</th>
        </tr>
      </thead>
      <tbody>
        <tr class="data-row"> 
          <td>09:00</td>
          <td>JAVIER IGNACIO MOLINA CANO</td>
          <td>LAVADO CORTE PEINADO</td>
          <td>$250,00</td>
          <td>GUSTAVO</td>
        </tr>
        
      </tbody>
    </table>
    <div class=banner>
        <label class=date ><i class=material-icons >account_balance_wallet</i> CAJA: $3465,00</label>
    </div>
</div>
<#include "/common/javascript.ftl">
</body>
</html>