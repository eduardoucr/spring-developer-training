package com.pfcti.springdata.service;
import com.pfcti.springdata.dto.ClienteDto;

import com.pfcti.springdata.dto.ProductoDto;
import com.pfcti.springdata.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ClienteServiceTest {


    @Autowired
    private ClienteService clienteService;

    @PersistenceContext
    private EntityManager   entityManager;
    @Test
    void insertarCliente() {
        List<Cliente> clienteList= entityManager.createQuery( "SELECT c  FROM Cliente c" ).getResultList();
        System.out.println("listar antes de insertar: " + clienteList);
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setApellidos("Hidalgo");
        clienteDto.setNombre("Eduardo");
        clienteDto.setCedula("1890000000");
        clienteDto.setTelefono("0999714563");

        clienteService.insertarCliente(clienteDto);
        clienteList = entityManager.createQuery("SELECT c FROM Cliente c").getResultList();
        assertFalse(clienteList.isEmpty());
        System.out.println("listar cuantos tiene: " + clienteList.size());
        assertEquals ("1890000000", clienteList.get(5).getCedula());
    }


    @Test
    void obtenerCliente() {
    ClienteDto clienteDto= clienteService.obtenerCliente(1);
    assertEquals(1, clienteDto.getId());

    }

    @Test
    void actualizarCliente() {
        //INSERT INTO CLIENTE (APELLIDOS, CEDULA, NOMBRE, TELEFONO) VALUES ('PEREZ', '1', 'ROBERTO', '093939393');

        ClienteDto clienteDtoBase = clienteService.obtenerCliente(1);
        System.out.println(clienteDtoBase);

        clienteDtoBase.setNombre(clienteDtoBase.getNombre() + "TEST");
        clienteDtoBase.setCedula(clienteDtoBase.getCedula() + "TEST");
        clienteDtoBase.setTelefono(clienteDtoBase.getTelefono() + "TEST");
        clienteDtoBase.setApellidos(clienteDtoBase.getApellidos() + "TEST");
        clienteService.actualizarCliente(clienteDtoBase);

        ClienteDto clienteDtoBaseUpdated = clienteService.obtenerCliente(1);

        System.out.println(clienteDtoBaseUpdated);
        assertEquals("ROBERTOTEST", clienteDtoBaseUpdated.getNombre());
        assertEquals("PEREZTEST", clienteDtoBaseUpdated.getApellidos());
    }

    @Test
    void eliminarCliente() {
        ClienteDto clienteDtoBase = clienteService.obtenerCliente(1);
        assertEquals(1, clienteDtoBase.getId());

        clienteService.eliminarCliente(1);

        try {
            clienteService.obtenerCliente(1);
            fail("No debe llegar aca");
        } catch (RuntimeException e) {
            System.out.println("CLIENTE NO EXISTE: " + e.getMessage());
        }
    }

    @Test
    void obtenerClientesPorCodigoISOPaisYCuentasActivas() {
        List<ClienteDto> clientesDto = clienteService.obtenerClientesPorCodigoISOPaisYCuentasActivas("CR");
        clientesDto.forEach(clienteDto -> {System.out.println("Cuentas Activas" + clienteDto);});
        assertEquals(1, clientesDto.size());}

    @Test
    void buscarClientesPorApellido() {
        List<Cliente> cliente =  clienteService.buscarClientesPorApellido("PEREZ");
        assertFalse(cliente.isEmpty());
        assertEquals("PEREZ", cliente.get(0).getApellidos());
    }

    @Test
    void obtenerClientesExtrajerosYTarjetasInactivas() {

        List<ClienteDto> clientesDto = clienteService.obtenerClientesExtrajerosYTarjetasInactivas("CR");
        clientesDto.forEach(clienteDto -> {System.out.println("Tarjetas Inactivas" + clienteDto);});
        assertEquals(1, clientesDto.size());}

    @Test
    void buscarClientesDinamicamentePorCriterio() {
        ClienteDto clienteDto= new ClienteDto();
        clienteDto.setNombre("ROBERTO");
        List<ClienteDto> resultadoCriteriosConDatosDTO = clienteService.buscarClientesDinamicamentePorCriterio(clienteDto);
        assertEquals(1,resultadoCriteriosConDatosDTO.size());
    }



    @Test
    void obtenerTodosLosProductosDeUnCliente() {
        ProductoDto productosDto = clienteService.obtenerTodosLosProductosDeUnCliente(1);

        assertEquals(1, productosDto.getCuentaDtos().size());
    }
}