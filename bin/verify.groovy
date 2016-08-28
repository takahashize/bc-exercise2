import org.bitcoinj.core.ECKey

import java.security.SignatureException

/**
 * verify message
 */

def d = "bip38passphraseSatoshibase32nb2hi4dthixs6z3joqxgs3zpoy3dkskk"
def signature = "IIEDS/EK1WJuH7/MpcwUkTn6uNzweS68rCAn6qEN0gh1VhqpFdkMA9NIhYS6cRdeRW8C4ptqhF3fZwdlpFXHPlU="

def pubkey = ECKey.fromPublicOnly("036fb4b2146c3b00ed4136d5de11912cee3c6239b3209fc60701c4b306fb57e223".decodeHex())
try {
    pubkey.verifyMessage(d, signature)
    println "verify ok"
} catch (SignatureException e) {
    println "verify failed" + e.getMessage()
}