package dev.datanorte.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    private final  MissoesRepository missoesRepository;
    private final  MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    //Listar todas as Missoes
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> Missoes = missoesRepository.findAll();
        return Missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    //Listar ninjas por Id
    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    //Cria missão
    public MissoesDTO criarMissao(MissoesDTO missoesDTO){
        MissoesModel missoes = new MissoesMapper().map(missoesDTO);
        missoes =  missoesRepository.save(missoes);
        return missoesMapper.map(missoes);
    }

    //Deleta missão

    public  Boolean deletarMissaoPorId(Long id) {
        if (missoesRepository.existsById(id)) {
            missoesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //atualizar missao
    public MissoesDTO atualizarMissao(Long id, MissoesDTO missoesDTO){
        Optional<MissoesModel> missaoExistente = missoesRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel MissaoSalvo = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(MissaoSalvo);
        }
        return null;
    }

}
