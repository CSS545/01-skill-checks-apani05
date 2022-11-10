import { StatusBar } from 'expo-status-bar';
import React, { useState, useEffect } from 'react';
import { StyleSheet, Text, View, Image, TextInput, TouchableOpacity} from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';

export default function App() {
  const [name, setName] = useState();
  const save = async () => {
    try {
      await AsyncStorage.setItem("MyName", name);
    } catch (err) {
      alert(err);
    }
  };
  const load = async () => {
    try {
      let name = await AsyncStorage.getItem("MyName");
      if (name !== null) {
        setName(name);
      }
    } catch (err) {
      alert(err)
    }
  };
  useEffect(() => {
    load();
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.name}>Hi {name}</Text>
      <Text style={styles.inputName}>What's your name?</Text>
      <TextInput style={styles.input} onChangeText={(text) => setName(text)} />
      <TouchableOpacity style={styles.button} onPress={() => save()}>
        <Text style={{ color: "#FFFFFF" }}> Save Name</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 100,
    backgroundColor: '#fff',
    alignItems: 'center',
  },
  name: {
    fontSize: 24,
    fontWeight: "300",
  },
  inputName: {
    fontSize: 18,
    paddingTop: 50,
    fontWeight: "200",
  },
  input: {
    borderWidth: 1,
    borderColor: "#333333",
    alignSelf: "stretch",
    margin: 32,
    height: 50,
    borderRadius: 6,
    paddingHorizontal: 10,
    fontWeight: "300",
    fontSize: 24,
  },
  button: {
    backgroundColor: "blue",
    alignItems: "center",
    justifyContent: "center",
    alignSelf: "stretch",
    paddingVertical: 12,
    marginTop: 10,
    marginHorizontal: 35,
    borderRadius: 6,
  },

});
