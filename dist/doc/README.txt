Gabriel Olteanu - 800128
Alessio Esposito Inchiostro - 847803

Práctica 1: Creación de un analizador léxico para el lenguaje ADAC. Para
compilar, ejecutar el comando "ant" en la carpeta raíz del proyecto.

Práctica 2: Analizador sintáctico En esta sección se explicará de manera breve
cómo el compilador genera reparación de errores para cada sección del código.
 
Cada programa se puede dividir en 3 partes: -La definición de las variables
globales -Las definición de las funciones y procedimientos -El cuerpo principal
del ejecutable (el "main")

VARIABLES GLOBALES: La primera parte está compuesta por 0 o más declaraciones,
es decir, una instrucción para definir una variable global. Para cada
instruccion, se consideran parte del conjunto de sincronizacion el ";" , el
inicializador de una nueva función o procedimiento, y el "begin", en caso de no
haber declaradas funciones y procedimientos.

Para el resto de instrucciones (que sea instanciar variables local o
efectivamente instrucciones de código), hemos elegido utilizar el ";" y el
"end", es decir, el separador a nivel de instrucción y el de nivel superior
(refiriéndonos a bucles e instrucciones condicionales a las que perteneciera
dicha instrucción).

FUNCS Y PROCS: Una función o procedimiento está definida por su cabecera, una
sección para la declaración de variables locales, y un bloque de sentencias.

Para la cabecera, hemos definido como único token de recuperación el "is", que
define el fin de la cabecera, mientras para la declaración de variables al igual
que antes, se utilizan el ";" y el "begin" que podría indicar el inicio del main
o cuerpo de la función. Para la declaración de parámetros de la función (o
procedimiento), hemos utilizado el token ")" y el "is". No pusimos el ";" porque
podría recuperarse a mitad de la lista de parámetros.

Para el bloque de sentencias, el "end" es el limitator natural, y no hay más.
Hemos añadido a las invocaciones a funciones el ")" como delimitador también,
para delimitar la zona donde se pasan los parámetros.

A los componentes de las expresiones les hemos puesto ")", "end", ";" y ",",
pero en concreto es muy difícil recuperar en modo pánico dentro de una sola
expresión, y por eso seguramente el ";" es más eficaz.

Práctica 3: Analizador semántico En esta sección se describirá la lógica del
analisís semantico. Se instancia la tabla de simbolos para guardar todos los
simbolos. Se tiene que tener en cuenta que no pueda existir una variable global
(nivel 0) que tenga el mismo nombre que el procedimiento principal. El análisis
es recursivo, y después de añadir a la tabla de simbolos las variables globales,
registra las funciones y sus parámetros. Hemos implementado el nivel 4 del
compilador, es decir, tanto variables escalares como vectores pueden ser
parámetros de funciones y procedimientos, sea por valor o por referencia. Para
las funciones, se busca que haya por los menos un return en su bloque de
sentencias. Además, en una instrucción condicional, si hay un return en el if
pero no hay return en el else, se lanzará un warning para informar al
programador de que podría tener problemas en tiempo de ejecución si el return no
llega a ejecutarse. Para los procedimientos, no hace falta return. En el cuerpo
de los procedimientos y funciones, las comprobaciones principales que hay que
hacer son 2:

-Comprobación del tipo de las expresiones: Se efectúa cada vez que se intenta
asignar una expresión a una variable. Comprueba que la expresión a la derecha
del ":=" sea toda del mismo tipo, y luego que el asignable a la izquierda sea
del mismo tipo que la expresión a la derecha (y que efectivamente sea algo
asignable).

-Invocaciones a funciones y procedimientos: Cada vez que se ejecuta una llamada
a función o procedimiento, se tiene que comprobar que el paso de parámetros sea
coherente con la función o procedimiento al que se quiere invocar. Lo primero
que se hace es contar el numero de parametros, y después se comprueba que los
tipos de los argumentos y de los parámetros coincidan. En el caso de parámetros
por referencia, el método expresion() devuelve un objeto Attributes que indica
si la expresión está compuesta por una sola variable, es decir una variable
simple, un array o un elemento de array, en cuyo caso el booleano referenciable
tendría valor verdadero. Además, si el parametro es un array, se comprueba que
el tamaño del array sea lo esperado. No está permitido invocar funciones fuera
de expresiones o procedimientos como parte de una expresión.

Práctica 4 - Generación de código Siendo que nuestro compilador en muchas
funciones devuelve recursivamente un objeto Attributes que contiene toda la
información necesaria para los análisis que se cumplen antes de la compilación,
hemos pensado que la mejor idea fuese añadir un campo CodeBlock a Attributes
para devolver también el bloque de código generado, y luego juntarlo y
devolverlo recursivamente hasta la función S(). Hemos anyadido un contador de
errores para comprobar si hay errores que puedan impedir la generación de
còdigo. Para el código del if, las etiquetas se generan de la siguente manera:
en la función inst_seleccion(), se genera una etiqueta de fin de if, y se supone
que también será la de fin del else. Si es encuentra el bloque else, se genera
su etiqueta normalmente. De hecho, si la condición es falsa, se ejecuta un salto
a la de fin del if, es decir, se ejecuta el bloque de código del else. En
cualquier otro caso, se ejecuta el if y luego se salta al else.

En cuanto a las variables: Para utilizar una varible por valor simplemente se
utilizan las instrucciones SRF y DRF para escribir su valor en la pila. Esto se
utiliza para utilizarlas en expresiones, pasarlas cómo parametros por valor y
para escribirlas por pantalla. Cuando se pasa un array por valor, se trata de
pushear todos los elementos del array en la pila, y luego cuando se invoca la
función considerar dicha zona de memoria la zona del "nuevo" array. En caso
contrario, para pasar variables por referencia, si son vectores simplemente se
utiliza el SRF para escribir en la pila sus direcciones. En caso de que fuese
necesario pasar como parametro por referencia una variable recibida por
referencia, en la sección de generación de código de inst_invoc_proc() y
inst_invoc_func() hay unas lineas de código que se ocupan de quitar/añadir un
DRF si fuese necesario.

Para el cálculo de las expresiones, se genera el código (osea el STC/STR y DRF,
y las instrucciones para los cálculos) mientras se leen los tokens.

Además, hemos añadido una comprobación en tiempo de ejecución para comprobar si
el índice utilizado para acceder a un elemento de array es correcto, y en caso
contrario se imprime un error y se termina la ejecución. Dicho chequeo se pone
en el código solo si la variable global genErrEjec está puesta true.

Para todas las otras producciones, no hemos tenido que añadir operaciones
especiales para que funcionen.
