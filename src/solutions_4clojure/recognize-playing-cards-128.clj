(fn poker [x]
  (let [suits {"D" :diamond
               "H" :heart
               "S" :spade
               "C" :club}
        ranks {"2" 0
               "3" 1
               "4" 2
               "5" 3
               "6" 4
               "7" 5
               "8" 6
               "9" 7
               "T" 8
               "J" 9
               "Q" 10
               "K" 11
               "A" 12}
        f (subs x 0 1)
        s (subs x 1 2)]
    {:suit (get suits f)
     :rank (get ranks s)}))