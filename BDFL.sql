#DROP DATABASE FantasyBD;
create database FantasyBD;
USE FantasyBD;

create table if not exists equipo(
 id int not null auto_increment primary key,
 nombre varchar(40) not null,
 logo varchar (100) not null
);

create table if not exists jugador(
 id int not null auto_increment primary key,
 nombre varchar(40) not null,
 posicion varchar(40) not null,
 precio int not null,
 fk_equipo int not null,
 foreign key (fk_equipo) references equipo (id)
);

create table if not exists jornada(
 id int not null auto_increment primary key,
 fecha timestamp default current_timestamp
 
 
);

create table if not exists partido(
 id int not null auto_increment primary key,
 e_local varchar(100) not null,
 visitante varchar (100) not null,
 resultado int,
 fk_jornada int not null,
 foreign key (fk_jornada) references jornada (id)
);

create table if not exists puntuacion(
 id int not null auto_increment primary key,
 puntuacionTotal int default 0,
 fk_partido int not null,
 foreign key (fk_partido) references partido (id)
);

create table if not exists puntuacion_jugador(
 id int not null auto_increment primary key,
 fk_puntuacion int not null,
 fk_jugador int not null,
 foreign key (fk_puntuacion) references puntuacion (id),
 foreign key (fk_jugador) references jugador (id)
);



create table if not exists liga(
 id int not null auto_increment primary key,
 nombre varchar(100) not null,
 descripcion varchar(100)
);

create table if not exists usuario(
 id int not null auto_increment primary key,
 nombre varchar(100) not null,
 apellido varchar(100) not null ,
 correo varchar(100) not null,
 alias varchar(100) not null ,
 contrasena varchar(100) not null,
 logo varchar(100),
 dinero int default 30000000,
 suma_puntuacion int default 0,
 fk_liga int not null,
 foreign key (fk_liga) references liga (id)
);

create table if not exists rosterFinal_usuario(
 id int not null auto_increment primary key,
 fk_usuario int not null,
 fk_jugador int not null,
 foreign key (fk_usuario) references usuario (id),
 foreign key (fk_jugador) references jugador (id)
);

create table if not exists roster_usuario(
 id int not null auto_increment primary key,
 fk_usuario int not null,
 fk_jugador int not null,
 foreign key (fk_usuario) references usuario (id),
 foreign key (fk_jugador) references jugador (id)
);

create table if not exists mercado(
 id int not null auto_increment primary key,
 fk_liga int not null,
 foreign key (fk_liga) references liga (id)
);
create table if not exists mercado_jugador(
 id int not null auto_increment primary key,
 fk_mercado int not null,
 fk_jugador int not null,
 foreign key (fk_mercado) references mercado (id),
 foreign key (fk_jugador) references jugador (id)
);

create table if not exists subasta(
 id int not null auto_increment primary key,
 dinero_ofrecido int not null,
 fk_jugador_subastado int not null,
 fk_usuario int not null,
 fk_mercado int not null,
 foreign key (fk_jugador_subastado) references jugador (id),
 foreign key (fk_mercado) references mercado (id),
 foreign key (fk_usuario) references usuario (id)
);

#MAÑANA
#1 PROCEDURES Y FUNCIONES BUSCARLAS
#2 TODOS LOS INSERTS NECESARIOS Y VISTAS (10 TABLAS)
#3 ECHAR UN VISTAZO A LOS AMIGOS
#4 TRIGGERS
#5 BUSCAR INFORMACION SOBRE MAVEN Y DEMAS TECNOLOGIAS PARA CREAR UN PROYECTO


#INSERTS DE EQUIPOS EN LA TABLA EQUIPOS
INSERT INTO equipo (nombre,logo)
values("EXCEL",""),
("FNATIC",""),
("G2",""),
("MISFITS",""),
("ORIGEN",""),
("ROGUE",""),
("SCHALKE 04",""),
("SK",""),
("SCHALKE 04",""),
("VITALITY","");


#INSERTS DE JUGADORES EN LA TABLA JUGADOR 

INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("EXPECT","TOP",1,1),
		("CAEDREL","JUNGLE",1,1),
        ("MICKEY","MID",1,1),
        ("PATRIK","ADC",1,1),
        ("TORE","SUPPORT",1,1),
        ("KASING","SUPPORT",1,1),
        ("SENDO","TOP",1,1),
        ("TAXER","JUNGLE",1,1),
        ("SPECIAL","MID",1,1),
        ("DEADLY","ADC",1,1),
        ("BOASTER","MID",1,1),
        ("YOUNGBACK/MAPACHE","COACH",1,1);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("BWIPO","TOP",1,2),
		("SELFMADE","JUNGLE",1,2),
        ("NEMESIS","MID",1,2),
        ("REKKLES","ADC",1,2),
        ("HYLISSANG","SUPPORT",1,2),
        ("PRIDE","TOP",1,2),
        ("DAN","JUNGLE",1,2),
        ("MAGIFELIX","MID",1,2),
        ("XMATTY","ADC",1,2),
        ("BRAVADO","SUPPORT",1,2),
        ("CHIBS","MID",1,2),
        ("MITHY","COACH",1,2);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("WUNDER","TOP",1,3),
		("JANKOS","JUNGLE",1,3),
        ("PERKZ","MID",1,3),
        ("CAPS","ADC",1,3),
        ("MIKYX","SUPPORT",1,3),
        ("OSCARININ","TOP",1,3),
        ("NASSER","JUNGLE",1,3),
        ("FEENIIXZ","MID",1,3),
        ("SUPA","ADC",1,3),
        ("AESENAR","SUPPORT",1,3),
        ("NANDISKO","TOP",1,3),
        ("P1NOY","ADC",1,3),
        ("GRABBZ","COACH",1,3);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("DAN DAN","TOP",1,4),
		("RAZORK","JUNGLE",1,4),
        ("FEBIVEN","MID",1,4),
        ("BVOY","ADC",1,4),
        ("DENYK","SUPPORT",1,4),
        ("AGRESIVOO","TOP",1,4),
        ("KIREI","JUNGLE",1,4),
        ("RONALDO","MID",1,4),
        ("JEZU","ADC",1,4),
        ("DOSS","SUPPORT",1,4),
        ("ROZARA","JUNGLE",1,4),
        ("DECAY","MID",1,4),
        ("JANDRO/AMAZING","COACH",1,4);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("ALPHARI","TOP",1,5),
		("XERXE","JUNGLE",1,5),
        ("NUKEDUCK","MID",1,5),
        ("UPSET","ADC",1,5),
        ("DESTINY","SUPPORT",1,5),
        ("MYRWN","TOP",1,5),
        ("CRONIIK","JUNGLE",1,5),
        ("KAMIKAZE","MID",1,5),
        ("CORVO","ADC",1,5),
        ("BOLY","SUPPORT",1,5),
        ("HIIVA","SUPPORT",1,5),
        ("GUILHOTO/KAYYS","COACH",1,5);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("FINN","TOP",1,6),
		("INSPIRED","JUNGLE",1,6),
        ("LARSSEN","MID",1,6),
        ("HANS SAMA","ADC",1,6),
        ("VANDER","SUPPORT",1,6),
        ("SZYGENDA","TOP",1,6),
        ("ZANZARAH","JUNGLE",1,6),
        ("CZEKOLAD","MID",1,6),
        ("WOOLITE","ADC",1,6),
        ("MYSTIQUES","SUPPORT",1,6),
        ("BLUEKNIGHT","MID",1,6),
        ("BLUMIGAN/FREDY122","COACH",1,6);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("ODOAMNE","TOP",1,7),
		("GILIUS","JUNGLE",1,7),
        ("ABBEDAGGE","MID",1,7),
        ("FORG1VEN","ADC",1,7),
        ("DREAMS","SUPPORT",1,7),
        ("SLEEPING","TOP",1,7),
        ("LUROX","JUNGLE",1,7),
        ("SERTUSS","MID",1,7),
        ("INNAXE","ADC",1,7),
        ("NEON","ADC",1,7),
        ("SIRNUKESALOT","SUPPORT",1,7),
        ("KAMITO","ADC",1,7),
        ("DYLAN FALCO","COACH",1,7);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("SACRE","TOP",1,8),
		("TRICK","JUNGLE",1,8),
        ("JENAX","MID",1,8),
        ("CROWNSHOT","ADC",1,8),
        ("LIMIT","SUPPORT",1,8),
        ("VENTAIR","TOP",1,8),
        ("PHRENIC","JUNGLE",1,8),
        ("REEKER","MID",1,8),
        ("TIGER","ADC",1,8),
        ("TRYMBI","SUPPORT",1,8),
        ("BERTHO","SUPPORT",1,8),
        ("UNLIMITED/KANANI","COACH",1,8);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("OROME","TOP",1,9),
		("SHADOW","JUNGLE",1,9),
        ("HUMANOID","MID",1,9),
        ("CARZZY","ADC",1,9),
        ("KAISER","SUPPORT",1,9),
        ("WERLYB","TOP",1,9),
        ("KOLDO","JUNGLE",1,9),
        ("HATRIXX","MID",1,9),
        ("FLAKKED","ADC",1,9),
        ("PRIME","SUPPORT",1,9),
        ("FALCO","SUPPORT",1,9),
        ("MAC/KAAS","COACH",1,9);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("CABOCHARD","TOP",1,10),
		("SKEANZ","JUNGLE",1,10),
        ("SAKEN","MID",1,10),
        ("COMP","ADC",1,10),
        ("JACTROLL","SUPPORT",1,10),
        ("MOWGLI","JUNGLE",1,10),
        ("NJI","JUNGLE",1,10),
        ("SELFIE","MID",1,10),
        ("LUCKER","ADC",1,10),
        ("TRAYTON","SUPPORT",1,10),
        ("STEEELBACK","SUPPORT",1,10),
        ("KREPO","SUPPORT",1,10),
        ("XANI","JUNGLE",1,10),
        ("DUKE/MEPHISTO","COACH",1,10);





