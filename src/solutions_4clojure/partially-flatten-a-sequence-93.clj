(fn partial-flatten [inp]
  ((fn helper [inp res]
     (if (and (sequential? inp)
              (sequential? (first inp)))
       (reduce concat (for [i inp]
                        (helper i res)))
       (conj res inp))) inp []))