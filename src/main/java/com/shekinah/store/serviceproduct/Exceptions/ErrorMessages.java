package com.shekinah.store.serviceproduct.Exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/***
 * Esta clase permite generar un mensaje personalizado de errores
 * Por tanto requiere
 *
 * CodError
 * Lista de Mensajes (Contiene el nombre del campo con error, y el mensaje del error que se ha producido en ese campo)
 */

@Data
@Builder
public class ErrorMessages {
    private String codeMessage;
    private List<Map<String, String>> listMessages;




}
