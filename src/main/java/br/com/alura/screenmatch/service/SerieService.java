package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    @Autowired
    private SerieRepository serieRepository;

    private List<SerieDTO> convertSeriesToDTO(List<Serie> series) {
        return series
                .stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(),
                        s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .toList();
    }

    public List<SerieDTO> getAllSeries() {
        return convertSeriesToDTO(serieRepository.findAll());


    }

    public List<SerieDTO> getTop5Series() {
        return convertSeriesToDTO(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }
}
