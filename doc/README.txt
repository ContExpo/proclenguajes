Gabriel Olteanu - 800128
Alessio Esposito Inchiostro - 847803

Pràctica 1: creaciòn de un analizador lèxico para el lenguaje ADAC.
Para compilar, ejecutar el comando "ant" en la carpeta raìz del proyecto

Pràctica 2: Analizador sintàctico
En esta secciòn se explicarà de manera discursiva como el compilador ejecuta
reparaciòn de errores para cada secciòn del còdigo.
 
Cada programa està compuesto por 3 partes :
-La definiciòn de las variables globales
-Las definiciòn de las funciones y proceduras
-El cuerpo principal del ejecutable (el "main")

VARIABLES GLOBALES
La primera parte està compuesta por 0 o màs elementos "declaracion", osea una
instrucciòn para definir variables globales. Para cada instruccion, se consideran
conjunto de sincronizacion el ; y el inicializador de una nueva funciòn o procedura
y el begin, si no hubiese procs y funcs declaradas.

Para todas las otras instrucciones (que sea instanciar variables local o efectivamente
instrucciones de còdigo, hemos elegido utilizar el ; y el end, osea el terminator
de nivel instruccion y lo de nivel superior (que sea un bucle, if o funciòn el nìvel
superior serìa màs instrucciones juntas).

FUNCS Y PROCS
Para esta seccion, no hay tokens de recuperaciòn porque es posible que no se tengan
subroutinas definidas, mientras para las singulas subrutinas hemos definidos conjunto.
Una subroutina està definida por su cabecera, la declaracion variables y las instrucciones

Para la cabecera, hemos definido còmo ùnico token la palabra "is", que define la
terminaciòn de la cabecera, mientras para la declaraciòn de variables còmo comentado
antes se utilizan el ; y el begin que podrìa indicar el empezar del main o del cuerpo
de la funcion.
Para la declaraciòn de parametros de la funciòn, hemos utilizado el token ")" y el
"is". No pusimos el ; porque podria quedarse en la mitad de la lista de parametros.


Para el bloque de sentencias, el END es el limitator natural, y no hay màs.
Hemos anyadido a las invocaciones a funciones el ) como limitator tambièn, para delimitar la zona donde se pasan los parametros.

A los componente de las expresiones hemos puesto ")", "end", ";" y ",", pero en concreto es muy dificil recuperar en modo panic dentro de una singula
expresiòn, y por eso seguramente el ; es el màs eficaz.

