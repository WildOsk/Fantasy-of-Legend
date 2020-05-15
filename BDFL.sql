DROP DATABASE FantasyBD;
create database FantasyBD;
USE FantasyBD;

create table if not exists equipo(
 id int not null auto_increment primary key,
 nombre varchar(40) not null,
 logo varchar(1000) 
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
 fecha timestamp
);

create table if not exists partido(
 id int not null auto_increment primary key,
 e_local int not null,
 e_visitante int not null,
 resultado varchar(100) default "0-0",
 fk_jornada int not null,
 foreign key (fk_jornada) references jornada (id),
 foreign key (e_local) references equipo(id),
 foreign key (e_visitante) references equipo(id)
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
 descripcion varchar(100),
 codigo varchar(100)
);
insert into liga values (0,"a","b","asvavab");


create table if not exists usuario(
 id int not null auto_increment primary key,
 nombre varchar(100) not null,
 apellido varchar(100) not null,
 rol varchar(100) not null default "otakus",
 correo varchar(100) not null,
 alias varchar(100) not null ,
 contrasena blob not null,
 logo varchar(100) default "../../assets/img/coffee2.jpg",
 dinero int default 30000000,
 suma_puntuacion int default 0,
 fk_liga int default 1 ,
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
values("EXCEL","https://gamepedia.cursecdn.com/lolesports_gamepedia_en/8/85/Excel_Esportslogo_profile.png"),
("FNATIC","https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/fc/Fnaticlogo_square.png/1200px-Fnaticlogo_square.png"),
("G2","https://3hmd7z41jo8fr2m9o2xkrln5-wpengine.netdna-ssl.com/wp-content/uploads/G2-Esports-2020-Logo-1.png"),
("MISFITS","https://i.blogs.es/d06c20/misfits-8fnvxt30/1366_2000.png"),
("ORIGEN","https://gamepedia.cursecdn.com/lolesports_gamepedia_en/1/12/Origenlogo_square.png"),
("ROGUE","https://gamepedia.cursecdn.com/lolesports_gamepedia_en/a/a4/Rogue_%28European_Team%29logo_square.png"),
("SCHALKE 04","https://upload.wikimedia.org/wikipedia/commons/9/97/FC_Schalke_04_Logo.png"),
("SK","https://esports.clashroyale.com/uploaded-images/sk_logo.png?mtime=20190531212514"),
("MAD LIONS","https://www.giantsgaming.pro/wp-content/uploads/2019/05/600px-MAD_Lionslogo_profile.png"),
("VITALITY","https://images.prismic.io/rivalryglhf/ee46659e6ccb4e4d7dab39d05ca09176b6390ed7_team-vitality-lcs-league-of-legends.png?auto=compress,format");


#INSERTS DE JUGADORES EN LA TABLA JUGADOR 

INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("EXPECT","TOP",15378787,1),
		("CAEDREL","JUNGLE",15909090,1),
        ("MICKEY","MID",21609848,1),
        ("PATRIK","ADC",22007575,1),
        ("TORE","SUPPORT",20946969,1),
        ("KASING","SUPPORT",1000000,1),
        ("SENDO","TOP",1000000,1),
        ("TAXER","JUNGLE",1000000,1),
        ("SPECIAL","MID",1000000,1),
        ("DEADLY","ADC",1000000,1),
        ("BOASTER","MID",1000000,1),
        ("YOUNGBACK/MAPACHE","COACH",21818181,1);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("BWIPO","TOP",15511363,2),
		("SELFMADE","JUNGLE",18428030,2),
        ("NEMESIS","MID",18162878,2),
        ("REKKLES","ADC",29034090,2),
        ("HYLISSANG","SUPPORT",16306818,2),
        ("PRIDE","TOP",1000000,2),
        ("DAN","JUNGLE",1000000,2),
        ("MAGIFELIX","MID",1000000,2),
        ("XMATTY","ADC",1000000,2),
        ("BRAVADO","SUPPORT",1000000,2),
        ("CHIBS","MID",1000000,2),
        ("MITHY","COACH",26520376,2);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("WUNDER","TOP",22670454,3),
		("JANKOS","JUNGLE",25454545,3),
        ("PERKZ","MID",26382575,3),
        ("CAPS","ADC",35000000,3),
        ("MIKYX","SUPPORT",27840909,3),
        ("OSCARININ","TOP",1000000,3),
        ("NASSER","JUNGLE",1000000,3),
        ("FEENIIXZ","MID",1000000,3),
        ("SUPA","ADC",1000000,3),
        ("AESENAR","SUPPORT",1000000,3),
        ("NANDISKO","TOP",1000000,3),
        ("P1NOY","ADC",1000000,3),
        ("GRABBZ","COACH",30000000,3);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("DAN DAN","TOP",17765151,4),
		("RAZORK","JUNGLE",19090909,4),
        ("FEBIVEN","MID",20946969,4),
        ("BVOY","ADC",23863636,4),
        ("DENYK","SUPPORT",22405303,4),
        ("AGRESIVOO","TOP",1000000,4),
        ("KIREI","JUNGLE",1000000,4),
        ("RONALDO","MID",1000000,4),
        ("JEZU","ADC",1000000,4),
        ("DOSS","SUPPORT",1000000,4),
        ("ROZARA","JUNGLE",1000000,4),
        ("DECAY","MID",1000000,4),
        ("JANDRO/AMAZING","COACH",24357366,4);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("ALPHARI","TOP",22007575,5),
		("XERXE","JUNGLE",19621212,5),
        ("NUKEDUCK","MID",19753787,5),
        ("UPSET","ADC",28503787,5),
        ("DESTINY","SUPPORT",18825757,5),
        ("MYRWN","TOP",1000000,5),
        ("CRONIIK","JUNGLE",1000000,5),
        ("KAMIKAZE","MID",1000000,5),
        ("CORVO","ADC",1000000,5),
        ("BOLY","SUPPORT",1000000,5),
        ("HIIVA","SUPPORT",1000000,5),
        ("GUILHOTO/KAYYS","COACH",27460815,5);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("FINN","TOP",17234848,6),
		("INSPIRED","JUNGLE",18030303,6),
        ("LARSSEN","MID",23333333,6),
        ("HANS SAMA","ADC",23068182,6),
        ("VANDER","SUPPORT",20549242,6),
        ("SZYGENDA","TOP",1000000,6),
        ("ZANZARAH","JUNGLE",1000000,6),
        ("CZEKOLAD","MID",1000000,6),
        ("WOOLITE","ADC",1000000,6),
        ("MYSTIQUES","SUPPORT",1000000,6),
        ("BLUEKNIGHT","MID",1000000,6),
        ("BLUMIGAN/FREDY122","COACH",27648902,6);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("ODOAMNE","TOP",14981061,7),
		("GILIUS","JUNGLE",4375000,7),
        ("ABBEDAGGE","MID",16041667,7),
        ("FORG1VEN","ADC",10340909,7),
        ("DREAMS","SUPPORT",13522727,7),
        ("SLEEPING","TOP",1000000,7),
        ("LUROX","JUNGLE",10075758,7),
        ("SERTUSS","MID",1000000,7),
        ("INNAXE","ADC",8352273,7),
        ("NEON","ADC",1000000,7),
        ("SIRNUKESALOT","SUPPORT",1000000,7),
        ("KAMITO","ADC",1000000,7),
        ("DYLAN FALCO","COACH",10438871,7);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("SACRE","TOP",8087121,8),
		("TRICK","JUNGLE",8352273,8),
        ("JENAX","MID",7689394,8),
        ("CROWNSHOT","ADC",15511364,8),
        ("LIMIT","SUPPORT",7556818,8),
        ("VENTAIR","TOP",1000000,8),
        ("PHRENIC","JUNGLE",1000000,8),
        ("REEKER","MID",1000000,8),
        ("TIGER","ADC",1000000,8),
        ("TRYMBI","SUPPORT",1000000,8),
        ("BERTHO","SUPPORT",1000000,8),
        ("UNLIMITED/KANANI","COACH",12319749,8);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("OROME","TOP",14981061,9),
		("SHADOW","JUNGLE",17897727,9),
        ("HUMANOID","MID",18958333,9),
        ("CARZZY","ADC",19886364,9),
        ("KAISER","SUPPORT",17367424,9),
        ("WERLYB","TOP",1000000,9),
        ("KOLDO","JUNGLE",1000000,9),
        ("HATRIXX","MID",1000000,9),
        ("FLAKKED","ADC",1000000,9),
        ("PRIME","SUPPORT",1000000,9),
        ("FALCO","SUPPORT",1000000,9),
        ("MAC/KAAS","COACH",27460815,9);
INSERT INTO jugador(nombre,posicion,precio,fk_equipo) 
values  ("CABOCHARD","TOP",8352273,10),
		("SKEANZ","JUNGLE",9810606,10),
        ("SAKEN","MID",5965909,10),
        ("COMP","ADC",10075758,10),
        ("JACTROLL","SUPPORT",4507576,10),
        ("MOWGLI","JUNGLE",1000000,10),
        ("NJI","JUNGLE",1000000,10),
        ("SELFIE","MID",1590909,10),
        ("LUCKER","ADC",1000000,10),
        ("TRAYTON","SUPPORT",1000000,10),
        ("STEEELBACK","SUPPORT",1856061,10),
        ("KREPO","SUPPORT",1000000,10),
        ("XANI","JUNGLE",1000000,10),
        ("DUKE/MEPHISTO","COACH",6771160,10);
       
INSERT INTO jornada (fecha)
values("2020-06-12 18:00:00"),
("2020-06-13 17:00:00"),
("2020-06-14 17:00:00");

INSERT INTO partido(e_local,e_visitante,fk_jornada)
values(9,3,1),
(10,7,1),
(5,8,1),
(6,1,1),
(4,2,1),
(7,8,2),
(6,4,2),
(1,9,2),
(2,10,2),
(3,5,2),
(8,9,3),
(7,6,3),
(5,4,3),
(3,10,3),
(2,1,3);

create or replace view num_posiciones
as select count(posicion) as "Total de supports", 
(select count(posicion) from jugador where posicion="ADC") as "Total de adcs",
(select count(posicion) from jugador where posicion="JUNGLE") as "Total de junglas",
(select count(posicion) from jugador where posicion="MID") as "Total de mids",
(select count(posicion) from jugador where posicion="TOP") as "Total de tops",
(select count(posicion) from jugador where posicion="COACH") as "Total de coachs"FROM jugador where posicion ="SUPPORT";

#CREACION DE VISTAS
create or replace view jug_valores
as select count(nombre) as" Jugadores con valor por mas de 20 MILLONES",
	(select count(nombre) from jugador where precio <20000000 and precio >10000000) as" Jugadores con valor entre 10 y 20 MILLONES",
	(select count(nombre) from jugador where precio <10000000 and precio >5000000) as" Jugadores con valor entre 10 y 5 MILLONES",
	(select count(nombre) from jugador where precio <5000000 and precio > 1000000) as" Jugadores con valor entre 5 MILLONES y 1 millon",
	(select count(nombre) from jugador where precio =1000000) as" Jugadores QUE NO HAN JUGADO y valen 1 millon"
from jugador where precio >20000000;


#VISTAS
SELECT * from num_posiciones;
select * from jug_valores;


