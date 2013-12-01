mail {
    server = 'localhost'
    port = 25
}

environments {
    dev {
        serverName = 'http://localhost:9090'        
    }
    
    test {
        serverName = 'http://testserver'
        mail {
            server = 'mail.testserver'
        }
    }
    
    prod {
        serverName = 'http://www.metafinanz.de'
        mail {
            port = 552
            server = 'mail.host.com'
        }
    }
}