# Considerações

No esquema de "Busy-Wait" a thread principal fica ativamente realizando uma espécie de verificação em "pooling", onde dentro do laço while, verifica o estado da thread se a mesma já terminou a sua execução a cada iteração.

Apesar de ser um técnica simples de implementar, que resolve o que se propõe a fazer (detectar a conclusão da tarefa assíncrona), utilizar "Busy-wait" pode não ser a melhor abordagem para o problema, como veremos a seguir:

## Implicações

### Alto Tempo de CPU

Ao utilizar o "Busy-Wait" na thread principal do código, faz com que esta thread esteja acordada o tempo todo, fazendo-se assim com que o tempo de CPU desta thread seja elevado, acarretando os seguintes problemas a seguir:

#### Alto custo em Cloud, especialmente em sistemas serverless

Muitas das plataformas de Cloud Computing, em especial as plataformas serverless, utilizam o tempo de CPU para o calculo do custo do sistema em cloud. Utilizar a técnica de Busy-wait em uma plataforma Cloud poderia elevar em muito os custos de operação.

#### Baixa performance do sistema

Ao elevar o tempo de CPU para essa thread, muitas vezes estamos impossibilitando, ou gerando concorrência, para outras threads do sistema, com uma thread que em si não está processando nada.

## Alternativas

Podemos colocar a thread para "dormir", não fazendo uso de CPU até que o recurso esteja disponível, no JAVA podemos fazer uso de: wait(), notify() e notifyAll()
