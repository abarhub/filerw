filerw
======

File read write with enum class  

Javadoc
=======

[Javadoc version 0.1.0](https://abarhub.github.io/filerw/documentation/version_0.1.0/apidocs)

[Javadoc version 0.27](https://abarhub.github.io/filerw/documentation/version_0.27/apidocs)

Explication
===========

[Documentation](https://abarhub.github.io/filerw/)

version 0.0.24 : compatible avec java 5
version 0.1.0 : compatible avec java 8

Exemple d'utilisation
=====================

Pour un fichier avec le format suivant :

| Nom du champs | position sur une ligne | taille de la ligne |
| ------------- |--------------:| -----:|
| nom     | 0 | 30 |
| prenom  | 30 | 30 |
| date de naissance | 60 | 8 |


Le séparateur est le retour à la ligne.

Exemple de fichier:

```
Newton                        Isaac                         04011643
Einstein                      Albert                        14103879
Copernic                      Nicolas                       19021473
```

Voici la classe pour définir le format du fichier :
```java
public enum FormatPersonnes implements Field {
	Nom(0, 30), Prenom(30, 30), DateNaissance(60, 8);

	private FieldsListChamps(int position, int length) {
		this.position = position;
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public int getPosition() {
		return position;
	}

	private int position;
	private int length;
}
```

Voici la classe pour lire et écrire ce fichier :
```java
public class LectureEcriturePersonnes{

	public void lecture(File f,File f_out) throws URISyntaxException, FileNotFoundException,
			IOException, ParseException {
		ReadWriteText<FieldsListChamps1> lecture;
		FileContentText<FieldsListChamps1> fichier;
		
		// lecture du fichier f
		System.out.println("Lecture du fichier " + f.getPath() + " :");
		lecture = new ReadWriteText<FieldsListChamps1>(f,FormatPersonnes.class);
		fichier = lecture.readFile();
		
		// affichage du contenu du fichier
		fichier.show();
		
		// ecriture du fichier vers f_out
		System.out.println("Ecriture du fichier " + f_out.getPath());
		lecture.writeFile(f_out, fichier);
	}

}
```

