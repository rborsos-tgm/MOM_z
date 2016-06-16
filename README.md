Implementieren Sie ein MOM-System, das zur Administration von Warteschlangen von Patienten bei der Aufnahme in einem Spital herangezogen werden kann. Jeder neue Patient bekommt eine eindeutige Nummer wird einer Warteschlange zugeordnet. Die Zuordnung der Warteschlange kann nach 2 verschiedenen Arten erfolgen: Round-Robin oder Least-Connection (wobei wir hier als Connection die Anzahl an Patienten in einer Warteschlange ansehen). Der Verteilungsmodus (Round-Robin / Least-Connection) kann mittels einem Befehl umgestellt werden. Der Arzt kann jeweils eine Warteschlange waehlen und bekommt die Nummer des naechsten Patienten in der Warteschlange -> mit dieser Aktion wird der Patient aus der Warteschlange entfernt. Ueberlegen Sie sich eine passende Syntax zu den notwendigen Befehle. Die Anzahl der Warteschlangen ist nicht begrenzt und kann jederzeit mit einem Befehl erhoeht werden. Wenn eine Warteschlange leer ist, dann kann diese Warteschlange auch wieder geloescht werden. Solange eine Warteschlange nicht leer ist, kann diese auch nicht geloescht werden.

Punkteverteilung:
1 Punkte: Implementierung Befehle fuer Patient / Arzt
1 Punkte: Administration der Warteschlangen (Hinzufuegen, Loeschen und Umstellung des Zuordnungsmodus)
3 Punkte: Implementierung Verteilungsmethode Round-Robin
3 Punkte: Implementierung Verteilungsmethode Least-Connection

Verwenden Sie Apache-Active-MQ als Message Broker.

Persoenlich Abnahme inklusive Abnahmegespraech erfolgt am 17.06.2016 um 12:30 Uhr. Wenn Sie an diesem Termin keine Zeit haben, dann bitte ich Sie mit mir Kontakt aufzunehmen, damit wir einen Ersatztermin suchen. Ein Abnahmegespraech ist unbedingt erforderlich!

Bitte Fragen an thomas.micheler@gmail.com senden!

Abzugeben sind (es wird einen eigene Abgabe in Moodle geben):
- Protokoll (wie bei Laboruebungen, inkl. UML Klassendiagramm u)
- Ausfuehrbare JAR-Dateien inklusive Quellcode
- API mittels Javadoc

Infos zu Round Robin und Leas-Connection:
http://www.itwissen.info/definition/lexikon/load-balancing-Lastausgleich.html
