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
 logo varchar(300),
 puntuacion_total int default 0,
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
 resultado varchar(100) default "No se ha jugado todavia",
 fk_jornada int not null,
 foreign key (fk_jornada) references jornada (id),
 foreign key (e_local) references equipo(id),
 foreign key (e_visitante) references equipo(id)
);

create table if not exists puntuacion_jugador(
 id int not null auto_increment primary key,
 fk_partido int not null,
 fk_jugador int not null,
 puntuacion int default 0,
 foreign key (fk_partido) references partido(id),
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
 dinero int default 60000000,
 suma_puntuacion int default 0,
 fk_liga int default 1 ,
 foreign key (fk_liga) references liga (id)
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

INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("EXPECT","TOP",15378787,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/d/df/XL_Expect_2020_Split_1.png",1),
		("CAEDREL","JUNGLE",15909090,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/8a/XL_Caedrel_2020_Split_1.png/220px-XL_Caedrel_2020_Split_1.png?version=5f5707735ff03f2986a3f1d68d11b69f",1),
        ("MICKEY","MID",21609848,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/9/91/XL_Mickey_2020_Split_1.png/220px-XL_Mickey_2020_Split_1.png?version=8ee295cccb82ba1eb8de8bd06d40095c",1),
        ("PATRIK","ADC",22007575,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/cf/XL_Patrik_2020_Split_1.png/220px-XL_Patrik_2020_Split_1.png?version=b424c733dacd89acfee75f6cf6f44edb",1),
        ("TORE","SUPPORT",20946969,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/37/XL_Tore_2020_Split_1.png/220px-XL_Tore_2020_Split_1.png?version=edf866729a0e587fa8b168da7be70cc5",1),
        ("KASING","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/ce/XL_kaSing_2019_Split_2.png/220px-XL_kaSing_2019_Split_2.png?version=fde78bf91207d1dd0bd6e7048eb2c92d",1),
        ("SENDO","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/5/51/XLUK_Send0o_2019_Split_2.png/220px-XLUK_Send0o_2019_Split_2.png?version=0b37d4cfea97ebf3a7c5e578f94b64fe",1),
        ("TAXER","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/34/XLUK_Taxer_2019_Split_2.png/220px-XLUK_Taxer_2019_Split_2.png?version=5248ef531b4f74b9fa5f80daedf26e63",1),
        ("SPECIAL","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/4/48/XL_Special_2019_Split_2.png/220px-XL_Special_2019_Split_2.png?version=d1856ba0c893abe02477994cec4c7fe1",1),
        ("DEADLY","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/b/b8/VGIA_Deadly_2019_Split_2.png/220px-VGIA_Deadly_2019_Split_2.png?version=f04448d9c142a9c3241e1e3ecaf4d6f1",1),
        ("BOASTER","MID",1000000,"",1),
        ("YOUNGBACK","COACH",21818181,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/88/FNC_YoungBuck_2019_Split_2.png/220px-FNC_YoungBuck_2019_Split_2.png?version=0820bd34fcfebd2d3073784e98008fc0",1);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("BWIPO","TOP",15511363,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/3d/FNC_Bwipo_2020_Split_1.png/220px-FNC_Bwipo_2020_Split_1.png?version=38ceadd53360c6bf7390024f0053b2a0",2),
		("SELFMADE","JUNGLE",18428030,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/14/FNC_Selfmade_2020_Split_1.png/220px-FNC_Selfmade_2020_Split_1.png?version=6268a7c27d45fd5d02c4a78cbe7fce49",2),
        ("NEMESIS","MID",18162878,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/1b/FNC_Nemesis_2020_Split_1.png/220px-FNC_Nemesis_2020_Split_1.png?version=b6b08856f7bc423d9701c9c924b3e77b",2),
        ("REKKLES","ADC",29034090,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/13/FNC_Rekkles_2020_Split_1.png/220px-FNC_Rekkles_2020_Split_1.png?version=047bbad331a3ec64248cf5baa9b7beec",2),
        ("HYLISSANG","SUPPORT",16306818,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/1a/FNC_Hylissang_2020_Split_1.png/220px-FNC_Hylissang_2020_Split_1.png?version=1e73c8159ff7c5f9cf804e3c1c0dbe49",2),
        ("PRIDE","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/1f/PFX_Pride_2019_Split_2.png/220px-PFX_Pride_2019_Split_2.png?version=fb4f16f65c3f34688db423f7d1dfa048",2),
        ("DAN","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/85/FNC_Dan_2019_Split_2.png/220px-FNC_Dan_2019_Split_2.png?version=33f8632ebdd1e9b18057cf427ebfd1e4",2),
        ("MAGIFELIX","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/32/FNCR_MagiFelix_2019_Split_2.png/220px-FNCR_MagiFelix_2019_Split_2.png?version=48e380985735a3bf2357812933ad905d",2),
        ("XMATTY","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/d/da/FNCR_xMatty_2019_Split_2.png/220px-FNCR_xMatty_2019_Split_2.png?version=57a659a81cc120c955e4f6541ef3cc16",2),
        ("BRAVADO","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/0/01/Bravado_June_2018.png/220px-Bravado_June_2018.png?version=bf3bce61188ce1bc6e23071b78fb2911",2),
        ("CHIBS","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/3d/MnM_Chibs_2019_Split_1.png/220px-MnM_Chibs_2019_Split_1.png?version=3c9e2bfa11f548602c7041e6b3c12d4b",2),
        ("MITHY","COACH",26520376,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/83/OG_Mithy_2019_Split_2.png/220px-OG_Mithy_2019_Split_2.png?version=95843daf399acf78bec458424c0361eb",2);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("WUNDER","TOP",22670454,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/ec/G2_Wunder_2020_Split_1.png/220px-G2_Wunder_2020_Split_1.png?version=30f4d24ba0b9aec4377aaf9b8bc31033",3),
		("JANKOS","JUNGLE",25454545,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/ed/G2_Jankos_2020_Split_1.png/220px-G2_Jankos_2020_Split_1.png?version=07b1a1f9ca2c3b47f41e9ca4abdbbaab",3),
        ("PERKZ","MID",26382575,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/0/08/G2_Perkz_2020_Split_1.png/220px-G2_Perkz_2020_Split_1.png?version=5d22f560b5651943e58038be2510dd31",3),
        ("CAPS","ADC",35000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/c5/G2_Caps_2020_Split_1.png/220px-G2_Caps_2020_Split_1.png?version=c93ba8bd938490424cc966d75fbca9a8",3),
        ("MIKYX","SUPPORT",27840909,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/fb/G2_Mikyx_2020_Split_1.png/220px-G2_Mikyx_2020_Split_1.png?version=3815b3b242885d472bd592f48b531ec8",3),
        ("OSCARININ","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/c7/G2A_Oscarinin_2020_Split_1.png/220px-G2A_Oscarinin_2020_Split_1.png?version=e230ce61405607cbd3d0dda967e395f4",3),
        ("NASSER","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/3c/G2A_Nasser_2020_Split_1.png/220px-G2A_Nasser_2020_Split_1.png?version=914f8991d827dcff71dcd1d51cf1b114",3),
        ("FEENIIXZ","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/5/52/G2A_FeeNiixZ_2020_Split_1.png/220px-G2A_FeeNiixZ_2020_Split_1.png?version=5bc0ea69959e1ef3b9ae76ad5a92a04e",3),
        ("SUPA","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/d/dc/G2A_Supa_2020_Split_1.png/220px-G2A_Supa_2020_Split_1.png?version=71a332b05f9bfdcb905f3c7738fa12fb",3),
        ("AESENAR","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/7/73/G2A_Aesenar_2020_Split_1.png/220px-G2A_Aesenar_2020_Split_1.png?version=84517719a08430e9764c6137095d9f03",3),
        ("NANDISKO","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/b/b0/TQ_Nandisko_2019_Split_2.png/220px-TQ_Nandisko_2019_Split_2.png?version=dd7215ddabdca795677b4d27a1612276",3),
        ("P1NOY","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/b/b1/ROG_P1noy_2019_Split_2.png?version=332181a7c99f6d76ca4e5c2ee60810b3",3),
        ("GRABBZ","COACH",30000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/a/a1/G2_GrabbZ_2019_WC.png/220px-G2_GrabbZ_2019_WC.png?version=e295a358cea12835f08e0226c57bb8fc",3);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("DAN DAN","TOP",17765151,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/0/0c/MSF_Dan_Dan_2020_Split_1.png/220px-MSF_Dan_Dan_2020_Split_1.png?version=aa82e12a9b699f48d47900893f903f28",4),
		("RAZORK","JUNGLE",19090909,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/b/bc/MSF_Razork_2020_Split_1.png/220px-MSF_Razork_2020_Split_1.png?version=ae2090b63a4f91c609f01c3abe974ef0",4),
        ("FEBIVEN","MID",20946969,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/0/00/MSF_Febiven_2020_Split_1.png/220px-MSF_Febiven_2020_Split_1.png?version=01fdbdbf0f9aa906afd68fd6ffb1ac68",4),
        ("BVOY","ADC",23863636,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/30/MSF_Bvoy_2020_Split_1.png/220px-MSF_Bvoy_2020_Split_1.png?version=a66436be5c4801c3ec8f4c08d9b86510",4),
        ("DENYK","SUPPORT",22405303,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/9/9a/MSF_Denyk_2020_Split_1.png/220px-MSF_Denyk_2020_Split_1.png?version=76b4cc44833d75f01aad449a744ea421",4),
        ("AGRESIVOO","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/d/de/MSF.P_Agresivoo_2020_Split_1.png/220px-MSF.P_Agresivoo_2020_Split_1.png?version=cd96649c12c240ec00ad76fa7f23aed9",4),
        ("KIREI","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/2/21/MSF.P_Kirei_2020_Split_1.png/220px-MSF.P_Kirei_2020_Split_1.png?version=47f3be96a5e5680eba54c02e72d9e49d",4),
        ("RONALDO","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/30/MSF_Ronaldo_2020_Split_1.png/220px-MSF_Ronaldo_2020_Split_1.png?version=4c5e81fec49ce28f5c927439c5a11b6f",4),
        ("JEZU","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/f9/MSF.P_Jezu_2020_Split_1.png/220px-MSF.P_Jezu_2020_Split_1.png?version=d294bdcd29d4ae3d118e2d377220ad3c",4),
        ("DOSS","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/0/08/MSF.P_Doss_2020_Split_1.png/220px-MSF.P_Doss_2020_Split_1.png?version=f661e9bdf3eb6f54a0fef4f65b410882",4),
        ("ROZARA","JUNGLE",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",4),
        ("DECAY","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/7/71/MSF.P_Decay_2020_Split_1.png/220px-MSF.P_Decay_2020_Split_1.png?version=2343cc1bedfa04ed8baaec6cb33ad42b",4),
        ("JANDRO","COACH",24357366,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/e7/FNCA_Jandro_2019_Split_1.png/220px-FNCA_Jandro_2019_Split_1.png?version=3674747c1c0ab5dfa84793cc64aa4ab9",4);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("ALPHARI","TOP",22007575,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/2/2a/OG_Alphari_2020_Split_1.png/220px-OG_Alphari_2020_Split_1.png?version=c902211bac2ac28b71967bc4b2ccabb8",5),
		("XERXE","JUNGLE",19621212,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/a/ab/OG_Xerxe_2020_Split_1.png/220px-OG_Xerxe_2020_Split_1.png?version=af9737219fea0647cbb4e6067a4d864d",5),
        ("NUKEDUCK","MID",19753787,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/ff/OG_Nukeduck_2020_Split_1.png/220px-OG_Nukeduck_2020_Split_1.png?version=0142fcd0c8040cdb39dca27eddfbfe23",5),
        ("UPSET","ADC",28503787,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/36/OG_Upset_2020_Split_1.png/220px-OG_Upset_2020_Split_1.png?version=2106d9123e46f14a4f94e8de23492b1a",5),
        ("DESTINY","SUPPORT",18825757,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/f7/OG_Destiny_2020_Split_1.png/220px-OG_Destiny_2020_Split_1.png?version=04fea3e24f13ce857a8ad36f22291b9d",5),
        ("MYRWN","TOP",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",5),
        ("CRONIIK","JUNGLE",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",5),
        ("KAMIKAZE","MID",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",5),
        ("CORVO","ADC",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",5),
        ("BOLY","SUPPORT",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",5),
        ("HIIVA","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/18/MSF_H1IVA_2019_Split_2.png/220px-MSF_H1IVA_2019_Split_2.png?version=12a40f824c4d953161d77ee3d2806930",5),
        ("GUILHOTO","COACH",27460815,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/0/0f/OG_Guilhoto_2019_Split_2.png/220px-OG_Guilhoto_2019_Split_2.png?version=15fcbb517443b70fb631b66f8867f363",5);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("FINN","TOP",17234848,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/87/RGE_Finn_2020_Split_1.png/220px-RGE_Finn_2020_Split_1.png?version=a71d366cac90737cb8087f9441466b57",6),
		("INSPIRED","JUNGLE",18030303,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/a/ad/RGE_Inspired_2020_Split_1.png/220px-RGE_Inspired_2020_Split_1.png?version=6d4e12e6a93c6ab17db370bebc1361d5",6),
        ("LARSSEN","MID",23333333,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/9/99/RGE_Larssen_2020_Split_1.png/220px-RGE_Larssen_2020_Split_1.png?version=ee309b352378ccba0b27aa98669221f8",6),
        ("HANS SAMA","ADC",23068182,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/7/73/RGE_Hans_Sama_2020_Split_1.png/220px-RGE_Hans_Sama_2020_Split_1.png?version=6b1865860da17e27d55bd89c4a081937",6),
        ("VANDER","SUPPORT",20549242,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/c9/RGE_Vander_2020_Split_1.png/220px-RGE_Vander_2020_Split_1.png?version=d56d3c4bee39f8748a092a6007880608",6),
        ("SZYGENDA","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/1/19/RGO_Szygenda_2020_Split_1.png?version=4ef8d8444e7d4ba0a1480cfceedaea57",6),
        ("ZANZARAH","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/0/02/RGO_Zanzarah_2020_Split_1.png?version=6346e488f7eead877c5c39193e090466",6),
        ("CZEKOLAD","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/2/22/RGO_Czekolad_2020_Split_1.png?version=3f4b28955a92457106d5a9293f0a7997",6),
        ("WOOLITE","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/b/b9/RGO_Woolite_2020_Split_1.png?version=034209d2c56f33c476a43a4ce793fa06",6),
        ("MYSTIQUES","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/c/c9/RGO_Mystiques_2020_Split_1.png?version=1d9dd50d3f7c890056105cf06073fd50",6),
        ("BLUEKNIGHT","MID",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",6),
        ("BLUMIGAN","COACH",27648902,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/d/d3/REC_Blumigan_2019_Split_1.png/220px-REC_Blumigan_2019_Split_1.png?version=4374e7044dc146d2868a88c4e34c1b6e",6);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("ODOAMNE","TOP",14981061,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/e8/S04_Odoamne_2020_Split_1.png/220px-S04_Odoamne_2020_Split_1.png?version=5f36495bea96a46a250da59627880bf6",7),
		("GILIUS","JUNGLE",4375000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/5/5e/S04_Gilius_2020_Split_1.png/220px-S04_Gilius_2020_Split_1.png?version=d7150d9fa41ef12a9004da60404f4533",7),
        ("ABBEDAGGE","MID",16041667,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/fc/S04_Abbedagge_2020_Split_1.png/220px-S04_Abbedagge_2020_Split_1.png?version=56b7fd2739f0d71565bef787b8f34fa3",7),
        ("FORG1VEN","ADC",10340909,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/10/S04_FORG1VEN_2020_Split_1.png/220px-S04_FORG1VEN_2020_Split_1.png?version=45a31e27b9cdaf95aba1034f06c44458",7),
        ("DREAMS","SUPPORT",13522727,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/9/98/S04_Dreams_2020_Split_1.png/220px-S04_Dreams_2020_Split_1.png?version=2210655a86fd79f9860283a90be535d9",7),
        ("SLEEPING","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/83/S04E_Sleeping_2020_Split_1.png/220px-S04E_Sleeping_2020_Split_1.png?version=4ce22b804bb0ec0a5db596ce66945aed",7),
        ("LUROX","JUNGLE",10075758,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/d/d1/S04_Lurox_2020_Split_1.png/220px-S04_Lurox_2020_Split_1.png?version=31b538fdf2c20a54e437c7b03da4433f",7),
        ("SERTUSS","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/a/a6/S04E_Sertuss_2020_Split_1.png/220px-S04E_Sertuss_2020_Split_1.png?version=00e0b75a99ec20a344a8295b61959ea3",7),
        ("INNAXE","ADC",8352273,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/c1/S04_Innaxe_2020_Split_1.png/220px-S04_Innaxe_2020_Split_1.png?version=14a6998eb95128117c151832b69c7ed0",7),
        ("NEON","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/34/S04E_Neon_2020_Split_1.png/220px-S04E_Neon_2020_Split_1.png?version=448be5136109d54ff61b87506f68cf08",7),
        ("SIRNUKESALOT","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/83/S04E_SirNukesAlot_2020_Split_1.png/220px-S04E_SirNukesAlot_2020_Split_1.png?version=a7825d63b43e20bbb70e97b8a247397a",7),
        ("KAMITO","ADC",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",7),
        ("FALCO","COACH",10438871,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/5/5c/S04_Dylan_Falco_2019_Split_2.png/220px-S04_Dylan_Falco_2019_Split_2.png?version=8e1ac131294a2b2cd9339728c14e5834",7);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("SACRE","TOP",8087121,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/6/65/SK_Sacre_2020_Split_1.png/220px-SK_Sacre_2020_Split_1.png?version=6a895b545849d18633b3c2a804bb73e4",8),
		("TRICK","JUNGLE",8352273,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/84/SK_Trick_2020_Split_1.png/220px-SK_Trick_2020_Split_1.png?version=b9d3997fd250089116712fad85e5059b",8),
        ("JENAX","MID",7689394,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/fc/SK_Jenax_2020_Split_1.png/220px-SK_Jenax_2020_Split_1.png?version=c5f16112747a694b4fe19ec683637419",8),
        ("CROWNSHOT","ADC",15511364,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/4/49/SK_Crownshot_2020_Split_1.png/220px-SK_Crownshot_2020_Split_1.png?version=3f897b61a315966356e9ed269e346feb",8),
        ("LIMIT","SUPPORT",7556818,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/6/66/SK_LIMIT_2020_Split_1.png/220px-SK_LIMIT_2020_Split_1.png?version=f0d18e644c65c8d2e92d98c0a3987de9",8),
        ("VENTAIR","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/2/2e/SKP_Ventair_2020_Split_1.png/220px-SKP_Ventair_2020_Split_1.png?version=92a29b879873a70f07afc8b038b1ca55",8),
        ("PHRENIC","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/ef/SKP_Phrenic_2020_Split_1.png/220px-SKP_Phrenic_2020_Split_1.png?version=d1436fbf035e6b49654947202eff9736",8),
        ("REEKER","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/c6/SKP_Reeker_2020_Split_1.png/220px-SKP_Reeker_2020_Split_1.png?version=199b6f5a6c5cefc04277bff17578fc80",8),
        ("TIGER","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/4/47/SKP_Tiger_2020_Split_1.png/220px-SKP_Tiger_2020_Split_1.png?version=f25d373e9222cdcbb3fbf0febea99051",8),
        ("TRYMBI","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/e1/SKP_Trymbi_2020_Split_1.png/220px-SKP_Trymbi_2020_Split_1.png?version=5e5a10e24c387e4d133b5baeedc809f8",8),
        ("BERTHO","SUPPORT",1000000,"https://gamepedia.cursecdn.com/commons_esports_gamepedia_en/thumb/1/1d/Unknown_Infobox_Image_-_Player.png/220px-Unknown_Infobox_Image_-_Player.png?version=636527c64463a523819c337326345d80",8),
        ("UNLIMITED","COACH",12319749,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/8/82/XLA_Unlimited_2019_Split_1.png/220px-XLA_Unlimited_2019_Split_1.png?version=6b8f80c10a6d951c2977ebb849bc8819",8);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("OROME","TOP",14981061,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/7/7c/MAD_Orome_2020_Split_1.png/220px-MAD_Orome_2020_Split_1.png?version=6e5b8a155f48827d089b99082efac7f7",9),
		("SHADOW","JUNGLE",17897727,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/fb/MAD_Shadow_2020_Split_1.png/220px-MAD_Shadow_2020_Split_1.png?version=8bebeb00f489899d4c439c644e90ec59",9),
        ("HUMANOID","MID",18958333,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/d/d0/MAD_Humanoid_2020_Split_1.png/220px-MAD_Humanoid_2020_Split_1.png?version=c985791bc3da0eeb9318fa36230030b8",9),
        ("CARZZY","ADC",19886364,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/2/2d/MAD_Carzzy_2020_Split_1.png/220px-MAD_Carzzy_2020_Split_1.png?version=fe418157e11a412bcca5a76402b3b349",9),
        ("KAISER","SUPPORT",17367424,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/6/68/MAD_Kaiser_2020_Split_1.png/220px-MAD_Kaiser_2020_Split_1.png?version=71ab2aa97da3d51bbf4b97c5a7167578",9),
        ("WERLYB","TOP",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/3c/MAD_Werlyb_2020_Split_1.png/220px-MAD_Werlyb_2020_Split_1.png?version=27196a06e5de8e27b9a192c5c27e1b77",9),
        ("KOLDO","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/2/2c/MAD_Koldo_2020_Split_1.png/220px-MAD_Koldo_2020_Split_1.png?version=6f615ff6ea2cec8edf118220040d8b5c",9),
        ("HATRIXX","MID",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/9/95/MAD_Hatrixx_2020_Split_1.png/220px-MAD_Hatrixx_2020_Split_1.png?version=f496561074a00c1c8d65b2a709d27357",9),
        ("FLAKKED","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/c7/MAD_Flakked_2020_Split_1.png/220px-MAD_Flakked_2020_Split_1.png?version=58820f1985e8d4fa3242d4ac5d09ca52",9),
        ("PRIME","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/5/51/MAD_Prime_2020_Split_1.png/220px-MAD_Prime_2020_Split_1.png?version=1a6f7dadefd91240ede0f0b9de2b65b7",9),
        ("FALCO","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/33/MAD_Falco_2020_Split_1.png/220px-MAD_Falco_2020_Split_1.png?version=c35c94f6ec2ad5e8e0cd26cf2420a506",9),
        ("MAC","COACH",27460815,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/2/2a/SPY_Mac.jpg/220px-SPY_Mac.jpg?version=57a61c59427afc7c1b5e4d15187533e6",9);
INSERT INTO jugador(nombre,posicion,precio,logo,fk_equipo) 
values  ("CABOCHARD","TOP",8352273,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/4/4c/VIT_Cabochard_2020_Split_1.png/220px-VIT_Cabochard_2020_Split_1.png?version=f794390801faf0b3aaf26ce1fdaec700",10),
		("SKEANZ","JUNGLE",9810606,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/6/60/VIT_Skeanz_2020_Split_1.png/220px-VIT_Skeanz_2020_Split_1.png?version=fb91fb2805b9103b96087b9f418d9044",10),
        ("SAKEN","MID",5965909,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/c/cc/VIT_Saken_2020_Split_1.png/220px-VIT_Saken_2020_Split_1.png?version=2a94a95aa0a258270058826351db86eb",10),
        ("COMP","ADC",10075758,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/4/41/VIT_Comp_2020_Split_1.png/220px-VIT_Comp_2020_Split_1.png?version=ef58c5673f9be5d0520ca315308c2ac7",10),
        ("JACTROLL","SUPPORT",4507576,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/f/f9/VIT_Jactroll_2020_Split_1.png/220px-VIT_Jactroll_2020_Split_1.png?version=75e49015871fc3ca6d827138c94dfb6f",10),
        ("MOWGLI","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/6/63/VIT_Mowgli_2019_Split_2.png/220px-VIT_Mowgli_2019_Split_2.png?version=d32e78c4fe0b7859bae2d05faf252df7",10),
        ("NJI","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/4/44/VIT.B_Nji_2020_Split_1.png/220px-VIT.B_Nji_2020_Split_1.png?version=e2ff203829b41125883c16a738d67301",10),
        ("SELFIE","MID",1590909,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/e0/VIT_Selfie_2020_Split_1.png/220px-VIT_Selfie_2020_Split_1.png?version=c4f731ed457abff2dd5a3e9ebd528928",10),
        ("LUCKER","ADC",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/1/15/VIT.B_Lucker_2020_Split_1.png/220px-VIT.B_Lucker_2020_Split_1.png?version=798f2ef7dd118d7b9c13e6526bd46f13",10),
        ("TRAYTON","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/1/1c/VIT_Trayton_2019_Split_2.png?version=86ca6cd57ac47eb5c34ca3ad4dbcd1fb",10),
        ("STEEELBACK","SUPPORT",1856061,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/6/6f/VIT.B_Steeelback_2020_Split_1.png/220px-VIT.B_Steeelback_2020_Split_1.png?version=47bb23cfe4a412590aea1c7d30f458ec",10),
        ("KREPO","SUPPORT",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/e/ef/El-krepo-2015spring.jpg/220px-El-krepo-2015spring.jpg?version=ca40d0a595e665fd9e4935aa334cdb3a",10),
        ("XANI","JUNGLE",1000000,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/3/3e/Xani.jpg/220px-Xani.jpg?version=5c15187cc8ec68670aa822d8c3e2e5d7",10),
        ("DUKE","COACH",6771160,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/thumb/4/40/SPY_Duke_2019_Split_2.png/220px-SPY_Duke_2019_Split_2.png?version=9fbf1564b02ffa2f7f9db0e2574ebc1d",10);
       
INSERT INTO jornada (fecha)
values("2020-06-12 18:00:00"),
("2020-06-13 17:00:00"),
("2020-06-14 17:00:00");

INSERT INTO partido(e_local,e_visitante,fk_jornada, resultado)
values(9,3,1, "https://www.giantsgaming.pro/wp-content/uploads/2019/05/600px-MAD_Lionslogo_profile.png"),
(10,7,1, "https://upload.wikimedia.org/wikipedia/commons/9/97/FC_Schalke_04_Logo.png"),
(5,8,1,"https://gamepedia.cursecdn.com/lolesports_gamepedia_en/1/12/Origenlogo_square.png"),
(6,1,1, "https://gamepedia.cursecdn.com/lolesports_gamepedia_en/8/85/Excel_Esportslogo_profile.png"),
(4,2,1,"https://i.blogs.es/d06c20/misfits-8fnvxt30/1366_2000.png"),
(7,8,2,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(6,4,2,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(1,9,2,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(2,10,2,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(3,5,2,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(8,9,3,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(7,6,3,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(5,4,3,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(3,10,3,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png"),
(2,1,3,"http://pngimg.com/uploads/hourglass/hourglass_PNG50.png");

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

#cuando se borre un usuario y si no quedan usuarios se borre la liga, se borre el mercado, con el fk de ese jugador

#drop trigger check_Usuarios;

DELIMITER $$
CREATE TRIGGER check_Usuarios
    AFTER DELETE ON usuario
    FOR EACH ROW 
		BEGIN
			declare numero int;
			IF ((select count(id) from usuario where fk_liga=old.fk_liga)=0) then
				DELETE FROM mercado_jugador WHERE fk_mercado = (select id from mercado where fk_liga = old.fk_liga);
                DELETE FROM mercado WHERE fk_liga = old.fk_liga;
                DELETE FROM liga WHERE id = old.fk_liga;   
			ELSE
				SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = "ADIOS";
			END IF;
        END$$
        
  DELIMITER $$
CREATE TRIGGER puntuacion
    AFTER insert ON puntuacion_jugador
    FOR EACH ROW 
		BEGIN
			UPDATE jugador SET puntuacion_total = puntuacion_total + new.puntuacion WHERE id = new.fk_jugador;
        END$$       
        
        
insert into puntuacion_jugador(fk_partido,fk_jugador, puntuacion) values
(1,100,12),(1,101,5),(1,102,8),(1,103,1),(1,107,23),(1,108,15),(1,109,10),(1,110,11),(1,111,15),
(1,25,14),(1,26,15),(1,27,22),(1,28,12),(1,29,5),(1,30,2),(1,31,30),(1,32,3),(1,33,10),(1,34,7),(1,35,1),(1,36,7),(1,37,4),

(2,112,12),(2,113,5),(2,114,8),(2,115,1),(2,116,23),(2,117,15),(2,118,10),(2,119,11),(2,120,2),(2,121,30),(2,122,3),(2,123,10),(2,124,7),(2,125,15),
(2,75,14),(2,76,15),(2,77,22),(2,78,12),(2,79,5),(2,80,2),(2,81,30),(2,82,3),(2,83,10),(2,84,7),(2,85,1),(2,86,7),(2,87,4),

(3,51,12),(3,52,5),(3,53,8),(3,54,1),(3,55,23),(3,56,15),(3,57,10),(3,58,11),(3,59,15),(3,60,2),(3,61,30),(3,62,3),
(3,88,14),(3,89,15),(3,90,22),(3,91,12),(3,92,5),(3,93,2),(3,94,30),(3,95,3),(3,96,10),(3,97,7),(3,98,1),(3,99,4),

(4,63,12),(4,64,5),(4,65,8),(4,66,1),(4,67,23),(4,68,15),(4,69,10),(4,70,11),(4,71,30),(4,72,3),(4,73,10),(4,74,15),
(4,1,14),(4,2,15),(4,3,22),(4,4,12),(4,5,5),(4,6,2),(4,7,30),(4,8,3),(4,9,10),(4,10,7),(4,11,1),(4,12,4),

(5,38,12),(5,39,5),(5,40,8),(5,41,1),(5,42,23),(5,43,15),(5,44,10),(5,45,11),(5,46,5),(5,47,2),(5,48,30),(5,49,3),(5,50,15),
(5,13,14),(5,14,15),(5,15,22),(5,16,12),(5,17,5),(5,18,2),(5,19,30),(5,20,3),(5,21,10),(5,22,7),(5,23,1),(5,24,7);



