# ahungry-dm

A display manager written in Clojure, because why not.

## Installation

This leverages PAM for the authentication, so needs to be set up.

### PAM configuration

```sh
cat /etc/pam.d/net-sf-jpam
#%PAM-1.0
auth            sufficient      pam_rootok.so
auth            required        pam_unix.so
account         required        pam_unix.so
session         required        pam_unix.so
password        required        pam_unix.so sha512 shadow
```
