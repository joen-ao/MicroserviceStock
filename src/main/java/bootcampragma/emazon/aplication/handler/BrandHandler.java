package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.BrandRequest;
import bootcampragma.emazon.aplication.mapper.BrandRequestMapper;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler {

    private final IBrandServicePort brandServicePort;
    private final BrandRequestMapper brandRequestMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        if(brandRequest == null){
            throw new IllegalArgumentException("Brand request cannot be null");
        }
        brandServicePort.saveBrand(brandRequestMapper.toRequest(brandRequest));
    }

}
