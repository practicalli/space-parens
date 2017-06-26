(ns space-parens.core)


;; Introducing some characters in our story

{:name "Rain Skydancer" :job "farmer" :immutable "high" :mutable "occasional"}

{:name "Obadia Kendo" :job "hermit" :immutable "high" :mutable "never"}


;; write a specification for an immutable rebel

;; Check that the map has keys: name, job, immutable, mutable plus their associated values.
;; The jobs must be of a known value (?)
;; The values for immutable and mutable must be of a known value (?)


(def space-parens-characters
  {:immutable-rebels
   [{:name "Rain Skydancer" :job "farmer" :immutable "high" :mutable "occasional"}
    {:name "Obadia Kendo"   :job "hermit" :immutable "high" :mutable "never"} ]})


;; planet - the simplest design whilst still providing contenxt for each value
{:name "bendora" :population "6000000" :resources-available {:unobtainium 24}}

;; We could drop the name from the collection and create a name for our plannet.  Doing this for all the planets in this way would mean creating all the planets up front.  Although this is flexible, it seems a little limited if we want to increase the number of planets during the running of the application.
(defn bendora {:population "6000000" :resources-available {:unobtainium 24}})


;; Planets belong to a solar system and there can be many planets in one solar system.  To have meaning to the values we use a map for each plannet.  As we can have multiple planets, we put each map as the element of a vector.
(def solar-systems
  {:name "sunora" :planets [{:name "bendora" :population "6000000" :resources-available {:unobtainium 24}}]})

;; If we have multiple solar-systems then we can put each solar-system in a vectore (like we did with planets previously).  This does mean we need code to iterate through each element of vector should we wish to search through each solar-system.
(def solar-systems
  [{:name "sunora" :planets [{:name "bendora" :population 6000000 :resources-available {:unobtainium 24}}]}
   {:name "hadron" :planets [{:name "headera" :population 8000    :resources-available {:unobtainium 98 :caustica 12}}]}])


;; Rather than put the collection of solar-systems in a vector, we can use a key as the name of a solar-system and the value of that key becomes the description of that solar system within a map.  This approach allows us to search through the nested maps with one function (get-in)
(def solar-systems
  {:sunora {:planets [{:name "bendora" :population 6000000 :resources-available {:unobtainium 24}}]}})


;; Using this nested map structure we can put all the planets and all the solar systems within one map.  We can abstract this map by giving it a name, solar-system, making it easy to refer to the map in functions or as arguments.
(def solar-systems
  {:sunora {:planets
            {:bendora
             {:population 6000000
              :resources-available {:unobtainium 24}}
             :gordana
             {:population 470
              :resources-available {:youthanasium 97}}}}
   :hadron {:planets
            {:headera {:population 8000
                       :resources-available {:unobtainium 98 :caustica 12}}
             :kritera {:population 350389 :resources-available {:bouncitanium 145}}}}})


;; By traversing the map we can pull out the relevant information we need.  For example, if we want the information about a particular solar-system we can get the details simply using the keyword that is the name of the solar system.
(get solar-systems :sunora)
;; => {:planets {:bendora {:population 6000000, :resources-available {:unobtainium 24}},
;;               :gordana {:population 470, :resources-available {:youthanasium 97}}}}


;; To get the details of the planets within a specific solar system, we can simply refine the results from the map.  When we got the solar system, we recieved a map.  So we can also use the same function to refine the result.
(get (get solar-systems :sunora) :planets)
;; => {:bendora {:population 6000000, :resources-available {:unobtainium 24}},
;;     :gordana {:population 470, :resources-available {:youthanasium 97}}}

;; We can go another level deeper in the map and return the details of a particular planet.
(get (get (get solar-systems :sunora) :planets) :bendora)
;; => {:population 6000000, :resources-available {:unobtainium 24}}

;; The get-in function is similar to the get function althought we can use it to simplfiy this expression and make it easier to read.
(get-in solar-systems [:sunora :planets :bendora])
;; => {:population 6000000, :resources-available {:unobtainium 24}}



(defn suitable-planet?
  "Return a list of suitable planets given the resources needed to build a base (both immutable rebel base and mutable empire),
  given a list of requirements (eg. resources, population, near enemy)"
  [solar-systems required-resource]
  (let [(keys required-resource)]))

(defn locate-new-home-for-rebel-base
  "Locate a new planet in one of the many solar systems that would make a suitable location for the next rebel base"
  []
  (do
    (println "Check the currently known star systems for available planets"))
  (str "Implement me: locate new home for rebel base"))

(defn build-rebel-base
  "Establish a new rebel base, using the local resources available"
  []
  (str "Implement me: build rebel base"))

;; arguments: solar-system, planet, resources-available (map),

;; Thoughts: when to use 1 argument or many?
;; - 1 argument can make the code to compose the function easier, ie. no need for partial or anonymous functions
;; - if a function has several arguemnts and you use it to iterate over a collection, then you need to wrap that function in the partical function or in an anonymous function.  Although this is a common practice, it does require a developer to parse the code more than they would otherwise.
