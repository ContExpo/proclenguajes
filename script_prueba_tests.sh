
#Este es un script para poder revisar los errores de todos los programas de prueba de una tajada

cd ./adac_pruebas

for nom_prueba in *.adac; do
	echo "Probando ${nom_prueba}:"
	java -jar ./dist/adac.jar < "$nom_prueba" | grep Error
	echo "Prueba concluida."
done
