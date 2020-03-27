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








