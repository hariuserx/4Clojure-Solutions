#(if (apply %1 [%2 %3])
   :lt
   (if (apply %1 [%3 %2])
     :gt
     :eq))