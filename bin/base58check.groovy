import org.bitcoinj.core.Base58

/**
 * Generate base58check(d)
 */

def d = "bip38passphraseSatoshibase32nb2hi4dthixs6z3joqxgs3zpoy3dkskk".bytes
println Utils.base58Check(d)

assert d == Base58.decodeChecked('2y7uDZedU8xyvnpRv3JVFt65hSHdnc7fTqAwG63LcTmAf355sRGBsYnwsQBHyhoL6uGVnp9Wwde6yx2PFW2wTXA3')
