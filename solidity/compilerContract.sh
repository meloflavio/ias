#!/bin/bash

set -e
SOLCVERSION='v0.8.1'
FILENAME=''
EXT=''
CONTRACT=Professional.sol
WEBJCLI="web3j"
UNZIP="tar -xvf"
#Verificar sistema operacional e definir url do compilador solc
if [[ "$OSTYPE" == "linux-gnu" ]]; then
    OS='linux'
    FILENAME='solc-static-linux'
elif [[ "$OSTYPE" == "darwin"* ]]; then
    OS='darwin'
    FILENAME='solc-macos'
else
    OS='windows'
    FILENAME='solc-windows'
    EXT=".exe"
    UNZIP='unzip'
    WEBJCLI="web3j.bat"
fi

PATHWEBJCLI=web3j/bin/$WEBJCLI
PATHSOLC=solc/solc$EXT

##Verifica se o arquivo binário ká existe se não inicia download
if [  -f "$PATHSOLC" ]; then
    echo 'Arquivo encontrado'
else
    curl -s https://api.github.com/repos/ethereum/solidity/releases | \
    grep -E "browser_download_url(.+)"$SOLCVERSION/$FILENAME$EXT | \
    cut -d : -f 2,3 \
    | tr -d \" \
    | xargs -I{} wget -O $PATHSOLC {}

    chmod +x $PATHSOLC
fi

for CONTRACT in contracts/* ; do
  if [[ $CONTRACT == *".sol"  ]]; then
    ./$PATHSOLC --bin --abi --optimize -o compiled $CONTRACT --overwrite
  fi
done

WEB3JVERSION="1.4.1"
if [  -f "$PATHWEBJCLI" ]; then
    echo 'Arquivo encontrado'
else
    curl -s https://api.github.com/repos/web3j/web3j-cli/releases | \
        grep -E "browser_download_url(.+)web3j-cli-shadow-$WEB3JVERSION.tar" | \
        cut -d : -f 2,3 \
        | tr -d \" \
        | xargs -I{} wget -O web3j-cli-shadow-$WEB3JVERSION.tar {}
    $UNZIP "web3j-cli-shadow-$WEB3JVERSION.tar"
    mv web3j-cli-shadow-$WEB3JVERSION web3j
    chmod +x $PATHWEBJCLI
fi

for CONTRACTCOMPILED in compiled/* ; do
  CONTRACTNAME=$(echo $CONTRACTCOMPILED| cut -d'.' -f 1)
  if [[ $CONTRACTCOMPILED == *".bin"  ]]; then
    echo $CONTRACTNAME
    $PATHWEBJCLI generate solidity  -a="$CONTRACTNAME.abi" -b="$CONTRACTCOMPILED" -o=../src/main/java -p=com.github.meloflavio.ias.contracts
  fi
done
