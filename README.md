# Team 19, Stargazers
## Making a Measurable Difference in the Quality of Citizenship

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Stargazers are a crew of full-time problem solvers seeking to promote social change by reinforcing positive trust between the citizens and their government.
Key features:
  - Multi-channel support for complaint registration (Twitter and Messenger Bot)
  - Analytical/Gamified dashboard to display stats for the nerds
  - REST APIs that provide the aggregated metrics over JSON

### Tech
Dillinger uses a number of open source projects to work properly:

* [Flask](https://flask.palletsprojects.com/en/1.1.x/) - for building smart bots, tweet analysis pipelines and NLP-based smart systems!
* [React](https://reactjs.org/) - HTML enhanced for web apps!
* [Spring Boot](https://spring.io/projects/spring-boot) - standalone, production-grade Spring applications

And our solution itself is open source with a [public repository](https://github.com/CFGIndia20/team-19)
 on GitHub.

### Folder Walkthrough

Our solution consists of various services working in synergy to ensure that civic body issues are reported and resolved in a timely order. All of the smart, independent services reside on the master branch of our [public repository](https://github.com/CFGIndia20/team-19) and you can find them as below:

| Folder | Service |
| ------ | ------ |
| [Analytics](https://github.com/CFGIndia20/team-19/tree/master/Analytics/janaagraha_analytics) | React dashboard for top citizens and states |
| [Analytics_API](https://github.com/CFGIndia20/team-19/tree/master/Analytics_API) | RESTful Spring Boot APIs consumed by the dashboard |
| [demo](https://github.com/CFGIndia20/team-19/tree/master/demo) | A simple Flask app to run the NLP model on a full-text field; Look into the [Demo Videos](https://github.com/CFGIndia20/team-19/tree/master/Demo%20Videos) folder for instructions to run |
| [Janaagraha Bot](https://github.com/CFGIndia20/team-19/tree/master/Janaagraha%20Bot) | simplistic Flask app that is used by the Messenger Bot |
| [Twitter Module](https://github.com/CFGIndia20/team-19/tree/master/TwitterModule) | smart tweet analysis pipeline consuming a stream of tweets to identify and register complaints |

### Todos

 - Build components for the analytics dashboard to drive user engagement
 - Add Success stories that reinforce a positive atmosphere and collaboration
 - Make REST APIs publish the data to a message queue so that multiple subscribers can consume the data

License
----

MIT


**Code For Social Good!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)


   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
