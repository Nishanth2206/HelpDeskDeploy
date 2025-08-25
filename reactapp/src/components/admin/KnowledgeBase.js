import React, { useState } from "react";


export default function KnowledgeBase() {
  const [articles, setArticles] = useState([
    "How to reset your password",
    "Troubleshooting network issues",
    "Installing company software",
  ]);
  const [newArticle, setNewArticle] = useState("");

  const handleAdd = () => {
    if (newArticle.trim() !== "" && !articles.includes(newArticle)) {
      setArticles([...articles, newArticle]);
      setNewArticle("");
    }
  };

  return (
   <>
    </>
  );
}
