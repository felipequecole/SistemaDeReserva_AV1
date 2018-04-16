## Instruções 
### Configurar server.xml
Lembrar de colocar no seu arquivos de configurações do apache (<path_apache>/conf/server.xml):
```
<Resource name="jdbc/SistemaReservaDB" auth="Container" type="javax.sql.DataSource"
            maxActive="100" maxIdle="30" maxWait="10000"
            username="SistemaReserva" password="sistemareserva"
            driverClassName="org.apache.derby.jdbc.ClientDriver"
                url="jdbc:derby://localhost:1527/SistemaReserva"/>
               
```
### Copiar arquivos do banco
- Copiar o conteúdo da pasta db deste repositório para a pasta de banco de dados configurada no seu apache
- (Se necessário) Fazer a ligação do banco de dados no netbeans
