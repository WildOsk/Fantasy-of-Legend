package com.Petin.FantasyOfLegend.Rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Petin.FantasyOfLegend.Dao.JugadoresDao;
import com.Petin.FantasyOfLegend.Dao.LigaDao;
import com.Petin.FantasyOfLegend.Dao.MercadoDao;
import com.Petin.FantasyOfLegend.Dao.SubastaDao;
import com.Petin.FantasyOfLegend.Dao.UsuarioDao;
import com.Petin.FantasyOfLegend.Entity.Jugador;
import com.Petin.FantasyOfLegend.Entity.Liga;
import com.Petin.FantasyOfLegend.Entity.Mercado;
import com.Petin.FantasyOfLegend.Entity.Subasta;
import com.Petin.FantasyOfLegend.Entity.Usuario;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class LigaImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UsuarioDao usu;

	@Autowired
	private LigaDao li;

	@Autowired
	private JugadoresDao jug;

	@Autowired
	private MercadoDao mer;

	@Autowired
	private SubastaDao sub;

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/union/{codigo}/{id}")
	public String[] unirse(@PathVariable String codigo, @PathVariable Integer id) {
		String[] resultadoString = new String[1];
		Query query = entityManager.createNativeQuery("Select nombre from liga where codigo= ?").setParameter(1,
				codigo);
		List<String> resultado = query.getResultList();
		if (resultado.size() != 0) {
			String nombre = resultado.get(0);
			query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1, nombre);
			List<Integer> resultList = query.getResultList();
			int idLiga = resultList.get(0);
			query = entityManager.createNativeQuery("Select id from usuario where fk_liga = ?").setParameter(1, idLiga);
			resultList = query.getResultList();
			if (resultList.size() < 6) {
				entityManager.createNativeQuery("Update Usuario set fk_liga=? where id= ?").setParameter(1, idLiga)
						.setParameter(2, id).executeUpdate();
				entityManager.close();
				resultadoString[0] = "success";
				return resultadoString;
			} else {
				resultadoString[0] = "No se pueden unir más personas a esta liga";
				return resultadoString;
			}

		} else {
			entityManager.close();
			resultadoString[0] = "No existe una liga con ese codigo";
			return resultadoString;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/comprobar/{id}")
	public Optional<Liga> comprobar(@PathVariable Integer id) {
		Query query = entityManager.createNativeQuery("Select fk_liga from usuario where id= ?").setParameter(1, id);
		int resultado = (int) query.getResultList().get(0);
		if (resultado != 1) {
			return li.findById(resultado);
		} else {
			return null;
		}
	}

	@PostMapping
	@RequestMapping("/liga/usuarios/{id}")
	public List<Optional> usuariosLiga(@PathVariable Integer id) {
		List<Optional> usuarios = new ArrayList<Optional>();
		Query query = entityManager.createNativeQuery("Select fk_liga from usuario where id= ?").setParameter(1, id);
		int resultado = (int) query.getResultList().get(0);
		query = entityManager.createNativeQuery("Select id from usuario where fk_liga = ?").setParameter(1, resultado);
		List<Integer> resultados = query.getResultList();
		for (int i = 0; i < resultados.size(); i++) {
			System.out.println(resultados.get(i));
			System.out.println(usu.findById(resultados.get(i)).toString());
			usuarios.add(usu.findById(resultados.get(i)));
		}
		return usuarios;
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/creacion/{id}")
	public String[] crearLiga(@RequestBody Liga liga, @PathVariable Integer id) {
		String[] resultado = new String[2];
		Query query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
				liga.getNombre());
		List<Integer> resultList = query.getResultList();
		System.out.println(resultList);
		if (resultList.size() == 0) {
			String codigoR = codigoRandom();
			entityManager.createNativeQuery("INSERT INTO Liga (nombre, descripcion, codigo) VALUES (?,?,?)")
					.setParameter(1, liga.getNombre()).setParameter(2, liga.getDescripcion()).setParameter(3, codigoR)
					.executeUpdate();
			query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
					liga.getNombre());
			resultList = query.getResultList();
			nuevoMercado(resultList.get(0));
			query = entityManager.createNativeQuery("Select id from liga where nombre= ?").setParameter(1,
					liga.getNombre());
			resultList = query.getResultList();
			asignarNuevaLiga(resultList.get(0), id);
			resultado[0] = "success";
			resultado[1] = codigoR;
			return resultado;
		} else {
			entityManager.close();
			resultado[0] = "Ya existe una liga con ese nombre.";
			return resultado;
		}
	}

	public String codigoRandom() {
		return RandomStringUtils.randomAlphanumeric(10);
	}

	public void nuevoMercado(int id) {
		entityManager.createNativeQuery("INSERT INTO Mercado (fk_liga) VALUES (?)").setParameter(1, id).executeUpdate();
		cambiarMercado(id, true);
	}

	public void asignarNuevaLiga(int id_liga, int id_usuario) {
		entityManager.createNativeQuery("Update Usuario set fk_liga=? where id= ?").setParameter(1, id_liga)
				.setParameter(2, id_usuario).executeUpdate();
		entityManager.close();
	}

	@Transactional(propagation = Propagation.NESTED)
	public void cambiarMercado(int id, boolean crear) {
		Query query = entityManager.createNativeQuery("Select id from Mercado where fk_liga = ?").setParameter(1, id);
		int idMercado = (int) query.getSingleResult();
		query = entityManager.createNativeQuery("Select id from Jugador");
		List<Integer> idJugadores = query.getResultList();
		query = entityManager.createNativeQuery("Select id from Usuario where fk_liga = ?").setParameter(1, id);
		List<Integer> idUsuarios = query.getResultList();
		for (int i = 0; i < idUsuarios.size(); i++) {
			query = entityManager.createNativeQuery("Select fk_jugador from roster_usuario where fk_usuario = ?")
					.setParameter(1, idUsuarios.get(i));
			List<Integer> jugadoresUsuario = query.getResultList();
			for (int j = 0; j < jugadoresUsuario.size(); j++) {
				idJugadores.remove(jugadoresUsuario.get(j));
			}
		}
		if (crear) {
			meterJugadores(idJugadores, idMercado);
		} else {
			cogerSubastaMasAlta(idMercado);
			cambiarJugadores(idJugadores, idMercado);
		}

	}

	@Transactional(propagation = Propagation.NESTED)
	public void meterJugadores(List<Integer> idJugadores, int idMercado) {
		System.out.println("meter");
		Random rand = new Random();
		List<Integer> jugadoresSubasta = new ArrayList<Integer>();
		while (jugadoresSubasta.size() < 6) {
			int nAleatorio = rand.nextInt(idJugadores.size());
			if (!jugadoresSubasta.contains(idJugadores.get(nAleatorio))) {
				jugadoresSubasta.add(idJugadores.get(nAleatorio));
			}
		}
		for (int i = 0; i < jugadoresSubasta.size(); i++) {
			entityManager.createNativeQuery("INSERT INTO Mercado_jugador (fk_mercado, fk_jugador) VALUES (?,?)")
					.setParameter(1, idMercado).setParameter(2, jugadoresSubasta.get(i)).executeUpdate();
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	public void cambiarJugadores(List<Integer> idJugadores, int idMercado) {
		System.out.println("cambiar");
		Random rand = new Random();
		List<Integer> jugadoresSubasta = new ArrayList<Integer>();
		while (jugadoresSubasta.size() < 6) {
			int nAleatorio = rand.nextInt(idJugadores.size());
			if (!jugadoresSubasta.contains(idJugadores.get(nAleatorio))) {
				jugadoresSubasta.add(idJugadores.get(nAleatorio));
			}
		}
		entityManager.createNativeQuery("DELETE from Mercado_jugador where fk_mercado=?").setParameter(1, idMercado).executeUpdate();
		for (int i = 0; i < jugadoresSubasta.size(); i++) {
			entityManager.createNativeQuery("INSERT INTO Mercado_jugador (fk_mercado, fk_jugador) VALUES (?,?)")
					.setParameter(1, idMercado).setParameter(2, jugadoresSubasta.get(i)).executeUpdate();
		}
	}

	@Scheduled(cron = "0 17 18 * * * ")
	@Transactional(propagation = Propagation.NESTED)
	public void mediaNoche() {
		Query query = entityManager.createNativeQuery("Select id from Liga");
		List<Integer> ligas = query.getResultList();
		ligas.remove(0);
		for (int i = 0; i < ligas.size(); i++) {
			cambiarMercado(ligas.get(i), false);
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/mercado/{id}")
	public List<Jugador> mostrarMercado(@PathVariable Integer id) {
		List<Jugador> jugadores = new ArrayList<>();
		Query query = entityManager.createNativeQuery("Select fk_liga from usuario where id= ?").setParameter(1, id);
		int resultado = (int) query.getResultList().get(0);
		if (resultado != 1) {
			query = entityManager.createNativeQuery("Select id from mercado where fk_liga= ?").setParameter(1,
					resultado);
			int mercado = (int) query.getResultList().get(0);
			query = entityManager.createNativeQuery("Select fk_jugador from mercado_jugador where fk_mercado= ?")
					.setParameter(1, mercado);
			List<Integer> ids = query.getResultList();
			for (int i = 0; i < ids.size(); i++) {
				Jugador j = jug.findById(ids.get(i)).get();
				jugadores.add(j);
			}
			for(int i=0;i<jugadores.size(); i++) {
				System.out.println(jugadores.get(i).toString());
			}
			
			return jugadores;
		} else {
			return null;
		}
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	public void cogerSubastaMasAlta(int idMercado) {
		System.out.println("Mercado = "+idMercado);
		List<Integer> jugadoresE = new ArrayList<Integer>();
		Query query2 = entityManager.createNativeQuery("select fk_jugador from mercado_jugador where fk_mercado = ?")
				.setParameter(1, idMercado);
		jugadoresE = query2.getResultList();
		System.out.println(jugadoresE);
		for (int i = 0; i < jugadoresE.size(); i++) {
			query2 = entityManager.createNativeQuery("select id from subasta where dinero_ofrecido in "
					+ "(select max(dinero_ofrecido) from subasta where fk_mercado = ? and fk_jugador_subastado = ?);")
					.setParameter(1, idMercado).setParameter(2, jugadoresE.get(i));
			
			if (query2.getResultList().size() > 0) {
				int resultado = (int) query2.getResultList().get(0);
				query2 = entityManager.createNativeQuery("select fk_usuario from subasta where id=?").setParameter(1,
						resultado);
				int idUsuario = (int) query2.getSingleResult();
				query2 = entityManager.createNativeQuery("select dinero_ofrecido from subasta where id=?")
						.setParameter(1, resultado);
				int dineroOfrecido = (int) query2.getSingleResult();
				System.out.println("añadido");
				entityManager.createNativeQuery("INSERT INTO roster_usuario (fk_usuario, fk_jugador) VALUES (?,?)")
						.setParameter(1, idUsuario).setParameter(2, jugadoresE.get(i)).executeUpdate();
				query2 = entityManager.createNativeQuery("select dinero from usuario where id=?").setParameter(1, idUsuario);
				int dinero = (int) query2.getSingleResult();
				int dineroFinal = dinero - dineroOfrecido;
				entityManager.createNativeQuery("Update Usuario set dinero= ? where id= ?").setParameter(1, dineroFinal)
						.setParameter(2, idUsuario).executeUpdate();
				entityManager.close();
			}
		}
		entityManager.createNativeQuery("DELETE from Subasta where fk_mercado = ?").setParameter(1, idMercado)
				.executeUpdate();
	}

	@Transactional(propagation = Propagation.NESTED)
	@PostMapping
	@RequestMapping("/liga/mercado/puja")
	public void pujaHecha(@RequestBody Subasta subasta) {
		System.out.println(subasta.toString());
		Query query = entityManager.createNativeQuery("select fk_liga from usuario where id = ?")
				.setParameter(1, subasta.getFk_usuario());
		int liga = (int) query.getResultList().get(0);
		
		query = entityManager.createNativeQuery("select id from mercado where fk_liga = ?")
				.setParameter(1, liga);
		int mercado= (int) query.getResultList().get(0);
		
		entityManager
				.createNativeQuery("Insert into Subasta (dinero_ofrecido,fk_jugador_subastado,fk_usuario, fk_mercado) "
						+ "values (?,?,?,?)")
				.setParameter(1, subasta.getDinero_ofrecido()).setParameter(2, subasta.getFk_jugador_subastado())
				.setParameter(3, subasta.getFk_usuario()).setParameter(4, mercado).executeUpdate();
	}
}
