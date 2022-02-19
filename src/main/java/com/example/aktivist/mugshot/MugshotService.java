package com.example.aktivist.mugshot;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MugshotService {


    private final MugshotRepository mugshotRepository;

    public List<Mugshot> getAllMugshots(){return  mugshotRepository.findAll();}

    public Optional<Mugshot> getMugshotById(String id){return mugshotRepository.findById(id);}

    public Mugshot saveMugshot(Mugshot mugshot){ return mugshotRepository.save(mugshot);}

    public List<Mugshot> saveAll(List<Mugshot> mugshots){ return mugshotRepository.saveAll(mugshots);}

    public Optional<Mugshot> updateMugshot(String id, Mugshot newMugshot){
        return mugshotRepository.findById(id)
                .map(oldMugshot -> {
                    Mugshot updated = oldMugshot.updateWith(newMugshot);
                    return mugshotRepository.save(updated);
                });
    }

    public void delete(String mugshotId) {mugshotRepository.deleteById(mugshotId);}
}
