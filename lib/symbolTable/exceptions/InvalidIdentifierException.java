/*********************************************************************************
 * Excepción utilizada al detectar una expresion que utiliza tipos incompatibles
 *
 * Fichero:    InvalidTypeExpression.java
 * Fecha:      02/03/2022
 * Versión:    v1.1
 * Asignatura: Procesadores de Lenguajes, curso 2021-2022, basado en código del 19-20
 **********************************************************************************/

package lib.symbolTable.exceptions;

public class InvalidIdentifierException extends Exception {

    String image;
	int line;
	int column;

	public InvalidIdentifierException(Token t) {
		this.line = t.beginLine;
		this.column = t.beginColumn;
        this.image = t.image;
	}

}
