package dev.datanorte.CadastroDeNinjas.Missoes;

import dev.datanorte.CadastroDeNinjas.Ninjas.NinjaDTO;
import dev.datanorte.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {
    private MissoesRepository missoesRepository;
    private MissoesMapper MissoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        MissoesMapper = missoesMapper;
    }

    //Listar todas as Missoes
    public List<MissoesDTO> listarMissoes(){
        List<MissoesModel> Missoes = missoesRepository.findAll();
        return Missoes.stream()
                .map(MissoesMapper::map)
                .collect(Collectors.toList());
    }

    //Listar ninjas por Id
    public MissoesDTO listarMissoesPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(MissoesMapper::map).orElse(null);
    }

    //Cria missão
    public MissoesDTO criarMissao(MissoesDTO missoesDTO){
        MissoesModel missoes = new MissoesMapper().map(missoesDTO);
        missoes =  missoesRepository.save(missoes);
        return MissoesMapper.map(missoes);
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
            MissoesModel missaoAtualizada = MissoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel MissaoSalvo = missoesRepository.save(missaoAtualizada);
            return MissoesMapper.map(MissaoSalvo);
        }
        return null;
    }

}
