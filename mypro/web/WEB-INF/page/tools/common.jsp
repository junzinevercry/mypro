<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<style type="text/css">
		        body,form,ul,li{margin:0; padding:0;}
		        body,p,th,td,span,div,a,input{color:#000000;font-size:9pt;font-family:Arial}
		        a{color:#000000;text-decoration:none}
		        a:hover{color:#ff0000;text-decoration:underline}
				input{width:50px}
				table{}
		</style>
	</head>
<body>
		<br/><br/>
        <form name="Keypad" action="">
            <table cellpadding="5" align="center">
                <tr>
                    <td>
                    </td>
                </tr>

                <tr>
                    <td colspan="3" align="middle"><input name=
                    "ReadOut" type="text" size="24" value="0"
                    width="100%" style="width:100%" /> </td>

                    <td>
                    </td>

                    <td><input name="btnClear" type="button" value=
                    " C " onclick="Clear()" /> </td>

                    <td><input name="btnClearEntry" type="button"
                    value=" CE " onclick="ClearEntry()" /> </td>
                </tr>

                <tr>
                    <td><input name="btnSeven" type="button" value=
                    " 7 " onclick="NumPressed(7)" /> </td>

                    <td><input name="btnEight" type="button" value=
                    " 8 " onclick="NumPressed(8)" /> </td>

                    <td><input name="btnNine" type="button" value=
                    " 9 " onclick="NumPressed(9)" /> </td>

                    <td>
                    </td>

                    <td><input name="btnNeg" type="button" value=
                    " +/- " onclick="Neg()" /> </td>

                    <td><input name="btnPercent" type="button"
                    value=" % " onclick="Percent()" /> </td>
                </tr>

                <tr>
                    <td><input name="btnFour" type="button" value=
                    " 4 " onclick="NumPressed(4)" /> </td>

                    <td><input name="btnFive" type="button" value=
                    " 5 " onclick="NumPressed(5)" /> </td>

                    <td><input name="btnSix" type="button" value=
                    " 6 " onclick="NumPressed(6)" /> </td>

                    <td>
                    </td>

                    <td align="middle"><input name="btnPlus" type=
                    "button" value=" + " onclick=
                    "Operation('+')" /> </td>

                    <td align="middle"><input name="btnMinus" type=
                    "button" value=" - " onclick=
                    "Operation('-')" /> </td>
                </tr>

                <tr>
                    <td><input name="btnOne" type="button" value=
                    " 1 " onclick="NumPressed(1)" /> </td>

                    <td><input name="btnTwo" type="button" value=
                    " 2 " onclick="NumPressed(2)" /> </td>

                    <td><input name="btnThree" type="button" value=
                    " 3 " onclick="NumPressed(3)" /> </td>

                    <td>
                    </td>

                    <td align="middle"><input name="btnMultiply"
                    type="button" value=" * " onclick=
                    "Operation('*')" /> </td>

                    <td align="middle"><input name="btnDivide"
                    type="button" value=" / " onclick=
                    "Operation('/')" /> </td>
                </tr>

                <tr>
                    <td><input name="btnZero" type="button" value=
                    " 0 " onclick="NumPressed(0)" /> </td>

                    <td><input name="btnDecimal" type="button"
                    value=" . " onclick="Decimal()" /> </td>

                    <td colspan="3">
                    </td>

                    <td><input name="btnEquals" type="button"
                    value=" = " onclick="Operation('=')" /> </td>
                </tr>
            </table>
        </form>
         <font face="Verdana, Arial, Helvetica" size="2">
<script type="text/javascript" language="JavaScript">
  
 
var FKeyPad = document.Keypad;   
var Accum = 0;   
var FlagNewNum = false;   
var PendingOp = "";   
function NumPressed (Num) {   
if (FlagNewNum) {   
FKeyPad.ReadOut.value  = Num;   
FlagNewNum = false;   
   }   
else {   
if (FKeyPad.ReadOut.value == "0")   
FKeyPad.ReadOut.value = Num;   
else   
FKeyPad.ReadOut.value += Num;   
   }   
}   
function Operation (Op) {   
var Readout = FKeyPad.ReadOut.value;   
if (FlagNewNum && PendingOp != "="){}
   
else   
{   
FlagNewNum = true;   
if ( '+' == PendingOp )   
Accum += parseFloat(Readout);   
else if ( '-' == PendingOp )   
Accum -= parseFloat(Readout);   
else if ( '/' == PendingOp )   
Accum /= parseFloat(Readout);   
else if ( '*' == PendingOp )   
Accum *= parseFloat(Readout);   
else   
Accum = parseFloat(Readout);   
FKeyPad.ReadOut.value = Accum;   
PendingOp = Op;   
   }   
}   
function Decimal () {   
var curReadOut = FKeyPad.ReadOut.value;   
if (FlagNewNum) {   
curReadOut = "0.";   
FlagNewNum = false;   
   }   
else   
{   
if (curReadOut.indexOf(".") == -1)   
curReadOut += ".";   
   }   
FKeyPad.ReadOut.value = curReadOut;   
}   
function ClearEntry () {   
FKeyPad.ReadOut.value = "0";   
FlagNewNum = true;   
}   
function Clear () {   
Accum = 0;   
PendingOp = "";   
ClearEntry();   
}   
function Neg () {   
FKeyPad.ReadOut.value = parseFloat(FKeyPad.ReadOut.value) * -1;   
}   
function Percent () {   
FKeyPad.ReadOut.value = (parseFloat(FKeyPad.ReadOut.value) / 100) * parseFloat(Accum);   
}   
</script>
        </font>
</body>
</html>