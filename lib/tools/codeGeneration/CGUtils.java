//*****************************************************************
// File:   CGUtils.java
// Author: Procesadores de Lenguajes-University of Zaragoza
// Date:   abril 2022
// Coms:   Librería con el método para crear etiquetas frescas y 
// 			una variable estática para llevar la traza de las 
// 			direcciones de memoria 
//*****************************************************************

package lib.tools.codeGeneration;

import lib.tools.codeGeneration.PCodeInstruction.OpCode;

public class CGUtils {
	
	private static int l=0; 
	
	public static String newLabel () {
		return "L"+(l++); 
	}
	
	// campo requeridos para la generación de código
	///Guarda la primera direcciòn libre para cada bloque
	public static int memorySpaces[] = new int[100];
	
}
