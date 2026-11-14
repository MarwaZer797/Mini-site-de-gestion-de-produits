Reponses du TP3 - Jakarta EE MVC

Nom & Prenom : Marwa Zerouali
Module : Jakarta EE / JEE
-----------------------------------------------
Question 1 : Pourquoi utilise-t-on un forward quand le login echoue, mais un sendRedirect quand il reussit ?

On utilise un forward lors d'un echec de connexion car cela permet de conserver les donnees de la requete et d'afficher directement la vue du formulaire de connexion (/login.jsp) sans changer l'URL dans le navigateur.

On utilise un sendRedirect en cas de succes afin d'informer le navigateur qu'il doit effectuer une nouvelle requete HTTP vers la page du tableau de bord (/dashboard), ce qui evita la re-soumission accidentelle du formulaire et initialise proprement la session utilisateur.
---------------------------------------------------
Question 2 : Que se passe-t-il si l'utilisateur appuie sur F5 juste apres avoir ajoute un produit, (a) si doPost fait un forward vers la liste, (b) si doPost fait un redirect? Comment s'appelle ce pattern ?

(a) Si le doPost fait un forward vers la liste, un appui sur F5 va re-executer la derniere requete HTTP (qui etait une requete POST d'ajout), ce qui va entrainer la creation en double du meme produit.

(b) Si le doPost fait un redirect (via une redirection HTTP), le navigateur effectue ensuite une requete GET vers la liste des produits. Un appui sur F5 se contente alors de recharger la liste en GET sans re-soumettre le formulaire.

Ce pattern s'appelle le pattern PRG (Post-Redirect-Get).
---------------------------------------------
Question 3 : Saisissez comme nom de produit alert('XSS'). Que se passe-t-il avec ${produit.nom} dans la liste? Et avec <c:out value="${produit.nom}"/> ? Concluez.
Avec ${produit.nom} (selon l'implementation standard de l'EL dans les versions recentes ou selon la configuration), les caracteres speciaux peuvent parfois ne pas etre securises automatiquement de la meme maniere selon le moteur, mais l'utilisation de <c:out value="${produit.nom}"/> echappe explicitement les caracteres HTML dangereux (comme les balises ) en les transformant en entites inoffensives (<script>).Conclusion : L'utilisation de <c:out> est indispensable pour se proteger contre les failles de type XSS (Cross-Site Scripting).
----------------------------------------------------

Question 4 : Quel est l'avantage du filtre par rapport a la verification dans chaque servlet ? Citez deux autres usages classiques des filtres.

L'avantage principal d'un filtre (comme AuthFilter) est la centralisation du code de controle d'acces : au lieu de repeter le meme test de session dans chaque servlet protegee, le filtre intercepte les requetes en amont, ce qui rend le code plus propre et evite les oublis de securite.

Deux autres usages classiques des filtres : la gestion de l'encodage des requetes en UTF-8 de maniere globale et la journalisation (logging) ou le suivi des performances des requetes.
