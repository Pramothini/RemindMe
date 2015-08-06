package exceptions;

import android.util.Log;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Helper class for ExceptionManager
 * @author Pramothini Dhandapany Kanchanamala
 */
public class Fix1to5 {

	String fix1(int errno, String errmsg){
		Log.e("LOG: "+new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) ,
                "Error No: "+errno+" Error msg: "+errmsg +"Error Fix :"+" Toast msg asking the user to enter a non empty value");
		return "Cannot create a new list without a name. Please provide a name for your list";
	}

	String fix2(int errno, String errmsg){
        Log.e("LOG: " + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()), "Error No: " + errno + " Error msg: " + errmsg + "Error Fix :" + " Toast msg asking the user to enter a non empty value");
        return "Cannot create list item without a name. Please provide a name for your list item";
	}

	String fix3(int errno, String errmsg){
        Log.e("LOG: " + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()), "Error No: " + errno + " Error msg: " + errmsg + "Error Fix :" + " Toast msg asking the user to enter a new unused list name");
        return "Cannot create a List as List name is already used. Please provide a new unused name for your List";
	}

	String fix4(int errno, String errmsg){
//		Log.INFO("LOG: " + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: " + errno + " Error msg: " + errmsg + "Error Fix :" + " Adding a default value as Ford");
		return "Ford";
	}

	int fix5(int errno, String errmsg){
//		Log.INFO("LOG: " + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date()) + " Error No: " + errno + " Error msg: " + errmsg + "Error Fix :" + " Adding a default value as 1212");
		return 1212;
	}
}
