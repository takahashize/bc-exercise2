/**
 * print various hash(d)
 */

def d = "bip38passphraseSatoshibase32nb2hi4dthixs6z3joqxgs3zpoy3dkskk".bytes

println "sha1: ${Utils.sha1(d)}"
println "sha256: ${Utils.sha256(d)}"
println "sha512: ${Utils.sha512(d)}"
println "sha256d: ${Utils.sha256d(d)}"
println "ripemd160: ${Utils.ripemd160(d)}"
println "sha256hash160: ${Utils.sha256hash160(d)}"
