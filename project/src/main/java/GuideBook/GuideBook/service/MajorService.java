package GuideBook.GuideBook.service;

import GuideBook.GuideBook.entity.GenreEntity;
import GuideBook.GuideBook.entity.MajorEntity;
import GuideBook.GuideBook.entity.MemberEntity;
import GuideBook.GuideBook.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public List<String> search(String indtroduce) {
        List<MajorEntity> majorEntityList = majorRepository.findByMajorNameContaining(indtroduce);
        List<String> majorNameList = new ArrayList<>();

        for (MajorEntity majorEntity : majorEntityList) {
            majorNameList.add(majorEntity.getMajorName());
        }

        return majorNameList;
    }
}
