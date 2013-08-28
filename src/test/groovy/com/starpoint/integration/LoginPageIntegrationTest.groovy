package com.starpoint.integration

import geb.spock.GebSpec

/**
 */
class LoginPageIntegrationTest extends GebSpec {

    def "login to app"() {
        when:
        go 'http://localhost:9191/broken/security/login'

        then:
        title == 'Broken1 Login'
    }

}
