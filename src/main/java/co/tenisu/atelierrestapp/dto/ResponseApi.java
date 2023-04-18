package co.tenisu.atelierrestapp.dto;

import co.tenisu.atelierrestapp.exception.BusinessErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseApi {

    private BusinessErrorCode statut;
    private Object response;

    public ResponseApi(BusinessErrorCode statut, Object response) {
        super();
        this.statut = statut;
        this.response = response;
    }

}
