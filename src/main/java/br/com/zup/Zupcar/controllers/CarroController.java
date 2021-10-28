package br.com.zup.Zupcar.controllers;

import br.com.zup.Zupcar.dtos.CarroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros") // Mapea as requisições para o endpoint nele contido
public class CarroController {
    private List<CarroDTO> concessionaria = new ArrayList<>();

    @GetMapping // é o Request Mapping utilizando o Verbo GET do PROTOCOLO HTTP
    public List<CarroDTO> exibirTodosOsCarros() {
        return concessionaria;
    }

    @PostMapping // é o Request Mapping utilizando o Verbo POST do PROTOCOLO HTTP
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
        // Todo Classe DTO são representações de Json seja de Entrada ou Saida.
        concessionaria.add(carroDTO);
    }

    @GetMapping("/{nomedoCarro}")
    public CarroDTO exibirCarro(@PathVariable String nomedoCarro) {

        for (CarroDTO objeto : concessionaria
        ) {
            if (objeto.getModelo().equalsIgnoreCase(nomedoCarro)) {
                return objeto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado");
    }

    //Método atualizar um carro com erro entregue no exercicio
   /* @PutMapping ("/{nomeDoCarro}")
    public CarroDTO alterarCarro  (@PathVariable String nomeDoCarro, String cor, String motor, int ano){
        CarroDTO carroDTO =  new CarroDTO();
        carroDTO.setModelo(nomeDoCarro);
        carroDTO.setCor(cor);
        carroDTO.setAno(ano);
        carroDTO.setMotor(motor);

        return carroDTO;
    }*/

    //Método correto que atualiza um carro

    @PutMapping("/{nomeDoCarro}")
    public CarroDTO atualizarCarro(@PathVariable String nomeDoCarro, @RequestBody CarroDTO carroDTO) {
        for (CarroDTO objetoDaLista : concessionaria) {
            if (objetoDaLista.getModelo().equals(nomeDoCarro)) {
                objetoDaLista.setAno(carroDTO.getAno());
                objetoDaLista.setCor(carroDTO.getCor());
                objetoDaLista.setMotor(carroDTO.getMotor());

                return objetoDaLista;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrei");
    }

    //Método que deleta um carro feito para o exercicio
    @DeleteMapping(value = "/{nomeDoCarro}")

    public void deletarCarro(@PathVariable String nomeDoCarro) {
        int contador = 0;
        for (CarroDTO objetoDaLista : concessionaria) {
            if (objetoDaLista.getModelo().equals(nomeDoCarro)) {
                concessionaria.remove(objetoDaLista);
                contador++;
            }
            if(contador ==0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrei");
            }
        }

    }
}