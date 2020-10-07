package kz.devhils.meathouse.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;
}
