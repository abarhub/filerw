filerw
======

[![Latest release](https://img.shields.io/github/release/abarhub/filerw.svg)](https://github.com/abarhub/filerw/releases/latest)
[![Build Status](https://travis-ci.com/abarhub/filerw.svg?branch=master)](https://travis-ci.com/abarhub/filerw)
[![Codecov](https://codecov.io/gh/abarhub/filerw/branch/master/graph/badge.svg)](https://codecov.io/gh/abarhub/filerw)
![Build workflow](https://github.com/abarhub/filerw/actions/workflows/maven.yml/badge.svg)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.abarhub.filerw/filerw/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.abarhub.filerw/filerw)

File read write with enum class  

Dépendance Maven
================

```xml
<dependency>
  <groupId>com.github.abarhub.filerw</groupId>
  <artifactId>filerw</artifactId>
  <version>0.1.0</version>
</dependency>
```

Javadoc
=======

[Javadoc version 0.1.0](https://abarhub.github.io/filerw/documentation/version_0.1.0/apidocs)

[Javadoc version 0.27](https://abarhub.github.io/filerw/documentation/version_0.27/apidocs)

Explication
===========

[Documentation](https://abarhub.github.io/filerw/)

version 0.1.0 : compatible avec java 8

Pour l'utiliser avec une version inferieur à java 5, il faut utiliser une version inférieure à la version 0.1.0.


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

