package com.gadawski.drools;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Qualifier("restTemplateImpl")
class RestTemplateImpl extends RestTemplate {
    // empty
}
